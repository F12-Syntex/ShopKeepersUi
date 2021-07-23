
package com.keepers.commands;

import java.util.List;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.keepers.main.ShopKeepers;
import com.keepers.utils.MessageUtils;

public class Help extends SubCommand {

	private ShopKeepers plugin = ShopKeepers.getInstance();

    @Override
    public void onCommand(Player player, String[] args) {
    	MessageUtils.sendHelp(player);
    }

    @Override

    public String name() {
        return plugin.CommandManager.help;
    }

    @Override
    public String info() {
        return "displays the help command";
    }

    @Override
    public String[] aliases() {
        return new String[0];
    }

	@Override
	public String permission() {
		return permissions.DEFAULT;	
	}
	
	@Override
	public AutoComplete autoComplete(CommandSender sender) {
		AutoComplete tabCompleter = new AutoComplete();
		
		List<SubCommand> commands = ShopKeepers.getInstance().CommandManager.getCommands();
		
		for(SubCommand i : commands) {
			tabCompleter.createEntry(i.name());
		}
		
		return tabCompleter;
	}
	

}