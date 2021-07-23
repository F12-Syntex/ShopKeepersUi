
package com.keepers.commands;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.keepers.GUI.PagedGUI;
import com.keepers.GUI.PagedItem;
import com.keepers.GUI.ShopData;
import com.keepers.GUI.ShopItemGUI;
import com.keepers.data.GenericMessages;
import com.keepers.data.VisualData;
import com.keepers.data.Visuals;
import com.keepers.utils.ComponentBuilder;
import com.keepers.utils.MessageUtils;
import com.keepers.utils.ShopUtils;

public class Search extends SubCommand {

    @Override
    public void onCommand(Player player, String[] args) {
    	
    	if(args.length < 2) {
    		MessageUtils.sendMessage(player, GenericMessages.instance().PREFIX + " " + "&c/shop search <item>");
    		return;
    	}
    
    	StringBuilder name = new StringBuilder();
    	
    	for(int i = 1; i < args.length; i++) {
    		name.append(args[i].toUpperCase() + "_");
    	}
    	
    	Material material = Material.valueOf(name.toString().substring(0, name.toString().length() - 1));

		Map<Material, List<ShopData>> catogaries = new HashMap<Material, List<ShopData>>();
		
		ShopUtils.get().forEach(shop -> {
		
			shop.getTradingRecipes(player).forEach(o -> {
				
				Material key = o.getResultItem().getType();
				
				ShopData data = new ShopData(shop, o);
				
				if(!o.isOutOfStock()) {
					if(catogaries.containsKey(key)) {
						catogaries.get(key).add(data);
					}else {
						List<ShopData> recipies = new ArrayList<ShopData>();
						recipies.add(data);
						catogaries.put(key, recipies);
					}
				}
				
			});
			
		});
		

		player.closeInventory();
		PagedGUI gui = new ShopItemGUI(player, catogaries.get(material));
		gui.open();
		
    }

    @Override

    public String name() {
        return "search";
    }

    @Override
    public String info() {
        return "Search for an item.";
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
		


		Player player = (Player)sender;
		
		List<PagedItem> items = new ArrayList<PagedItem>();

			Map<Material, List<ShopData>> catogaries = new HashMap<Material, List<ShopData>>();
			
			ShopUtils.get().forEach(shop -> {
			
				shop.getTradingRecipes(player).forEach(o -> {
					
					Material key = o.getResultItem().getType();
					
					ShopData data = new ShopData(shop, o);
					
					if(!o.isOutOfStock()) {
						if(catogaries.containsKey(key)) {
							catogaries.get(key).add(data);
						}else {
							List<ShopData> recipies = new ArrayList<ShopData>();
							recipies.add(data);
							catogaries.put(key, recipies);
							tabCompleter.createEntry(key.name().toLowerCase().replace("_", " "));
						}
					}
					
				});
				
			});
			

			catogaries.keySet().forEach(key -> {
				
				VisualData category = Visuals.instance().getCategory(catogaries.get(key), key);
				
				ItemStack item = new ItemStack(key);
			
				ItemMeta meta = item.getItemMeta();
				meta.setDisplayName(MessageUtils.translateAlternateColorCodes(category.getName()));
				
				meta.setLore(ComponentBuilder.createLore(category.getLore()));
				
				item.setItemMeta(meta);
				
				items.add(new PagedItem(item, () -> {
					player.closeInventory();
					PagedGUI gui = new ShopItemGUI(player, catogaries.get(key));
					gui.open();
				}));
				
			});
		
		
		
		
		
		return tabCompleter;
	}

}