package com.keepers.commands;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import com.keepers.cooldown.CooldownUser;
import com.keepers.cooldown.Cooldowns;
import com.keepers.data.GenericMessages;
import com.keepers.main.ShopKeepers;
import com.keepers.tags.TagFactory;
import com.keepers.utils.MessageUtils;
import com.keepers.utils.StringMinipulation;

public class CommandManager implements CommandExecutor {

    private ArrayList<SubCommand> commands = new ArrayList<SubCommand>();
    private ShopKeepers plugin;

    //Sub Commands
    public String main = "shop";
    public String help = "help";

    
    public void setup(ShopKeepers plugin) {
    	plugin.getCommand(main).setExecutor(this);
        commands.add(new Help());
        commands.add(new Reload());
        commands.add(new Open());
        commands.add(new Search());
    
        
        plugin.getCommand(main).setTabCompleter(new TabCompleter() {
			@Override
			public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
				
				if(args.length == 1) {
				
					List<String> tabCommands = new ArrayList<String>();
					
					for(SubCommand i : commands) {
						tabCommands.add(i.name());
					}
					return tabCommands;
				}
	  
				SubCommand parent = get(args[0]);
				
				AutoComplete completer = null;
				
				try {
					completer = parent.autoComplete(sender);
				}catch (Exception e) {
					return new ArrayList<String>();
				}
				
				if(completer == null) {
					return new ArrayList<String>();
				}
				
				String filter = "";
				
				for(int i = 0; i < args.length - 1; i++) {
					filter += args[i] + ".";	
				}
				
				List<String> values = completer.filter(StringMinipulation.removeLastCharOptional(filter));
				
				return values;
			}
		});
        
    }


    public ArrayList<SubCommand> getCommands(){
    	return commands;
    }

    
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {


        if (!(sender instanceof Player)) {

            sender.sendMessage(GenericMessages.instance().INAVLID_ENTITY);

            return true;

        }

        Player player = (Player) sender;
        
    	try {

        if (command.getName().equalsIgnoreCase(main)) {

            if (args.length == 0) {
            	
            	CooldownUser user = ShopKeepers.getInstance().cooldownManager.getUser(player.getUniqueId());
            	
            	SubCommand cmd = new Open();
            	
            	if(!player.hasPermission(cmd.permission())) {
		    		MessageUtils.sendRawMessage(player, GenericMessages.instance().INAVLID_PERMISSION);
		    		return true;
		        }
            	
            	
            	String key = cmd.name();

            	int timer = user.getTime(key);
            	
            	if(timer <= 0 || player.hasPermission(com.keepers.data.Permissions.instance().BYPASS)) {
            	
            		cmd.onCommand(player, args);
            	  
                	user.reset(key);
                
            	}else {
                	
                	TagFactory tagHelper = TagFactory.instance(Cooldowns.instance.message);
                
                	tagHelper.setCooldown(timer);
                	
                	MessageUtils.sendRawMessage(player, tagHelper.parse());
                }
            	
                return true;

            }

            SubCommand target = this.get(args[0]);

            if (target == null) {

                player.sendMessage(GenericMessages.instance().INVALID_SYNTEX);

                return true;

            }
            
		    if(!player.hasPermission(target.permission())) {
		    	MessageUtils.sendRawMessage(player, GenericMessages.instance().INAVLID_PERMISSION);
		    		return true;
		    }

            ArrayList<String> arrayList = new ArrayList<String>();

            arrayList.addAll(Arrays.asList(args));

            arrayList.remove(0);
            
            try{
            	
            	CooldownUser user = ShopKeepers.getInstance().cooldownManager.getUser(player.getUniqueId());
            	
            	String key = args[0].trim();

            	int timer = user.getTime(key);
            	
            	if(timer <= 0 || player.hasPermission(com.keepers.data.Permissions.instance.BYPASS)) {
            		
            	    target.onCommand(player, args);
            	    
            	    user.reset(key);
                
            	}else {
                	
                	TagFactory tagHelper = TagFactory.instance(Cooldowns.instance().message);
                
                	tagHelper.setCooldown(timer);
                	
                	MessageUtils.sendRawMessage(player, tagHelper.parse());
                }
            	
            
            
            }catch (Exception e){
                player.sendMessage(GenericMessages.instance().ERROR);
                e.printStackTrace();
            }

        }


    }catch(Throwable e) {
        player.sendMessage(GenericMessages.instance().ERROR);
        e.printStackTrace();
    }

        return true;
    
    }



    public SubCommand get(String name) {

        Iterator<SubCommand> subcommands = commands.iterator();

        while (subcommands.hasNext()) {

            SubCommand sc = (SubCommand) subcommands.next();


            if (sc.name().equalsIgnoreCase(name)) {

                return sc;

            }


            String[] aliases;

            int length = (aliases = sc.aliases()).length;



            for (int var5 = 0; var5 < length; ++var5) {

                String alias = aliases[var5];

                if (name.equalsIgnoreCase(alias)) {

                    return sc;

                }

            }

        }

        return null;

    }


	public ShopKeepers getPlugin() {
		return plugin;
	}


	public void setPlugin(ShopKeepers plugin) {
		this.plugin = plugin;
	}

}