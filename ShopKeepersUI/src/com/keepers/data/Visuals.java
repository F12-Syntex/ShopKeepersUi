package com.keepers.data;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;

import com.keepers.GUI.ShopData;
import com.keepers.main.ShopKeepers;
import com.keepers.utils.ComponentBuilder;
import com.keepers.utils.MessageUtils;
import com.nisovin.shopkeepers.api.shopkeeper.Shopkeeper;
import com.nisovin.shopkeepers.api.shopkeeper.TradingRecipe;

public class Visuals {

	private static Visuals instance;
	
	public FileConfiguration config = ShopKeepers.getInstance().configManager.getConfig("visuals").configuration;

	public VisualData getCategory(List<ShopData> list, Material key) {
		
		String name = MessageUtils.translateAlternateColorCodes(config.getString("Visuals.Category.Items.Name")).replace("%category%", key.name()).replace("%category%", key.name()).replace("%shops%", list.size()+"");
		
		List<String> lore = ComponentBuilder.createLore(config.getStringList("Visuals.Category.Items.Lore"));
		
		for(int i = 0; i < lore.size(); i++) {
			final String temp = lore.get(i);
			lore.set(i, temp.replace("%category%", key.name()).replace("%category%", key.name()).replace("%shops%", list.size()+""));
		}
		
		VisualData data = new VisualData(name, lore);
		
		return data;
		
	}
	
	public VisualData getSelectionWithOneItem(TradingRecipe recipe, Shopkeeper shop) {
		
		String location = shop.getX() + ", " + shop.getY() + ", " + shop.getZ();
		
		String name = MessageUtils.translateAlternateColorCodes(config.getString("Visuals.Selection.Name")
				.replace("%item%", recipe.getResultItem().getType().name())
				.replace("%trade1%", recipe.getItem1().getType().name())
				.replace("%amount%", recipe.getItem1().getAmount()+"")
				.replace("%location%", location));
		
		List<String> lore = ComponentBuilder.createLore(config.getStringList("Visuals.Selection.OneItemTrade.Lore"));
		
		List<String> newLore = new ArrayList<String>();
		
		for(int i = 0; i < lore.size(); i++) {
					String newString = lore.get(i).replace("%item%", recipe.getResultItem().getType().name());
					newString = newString.replace("%trade1%", recipe.getItem1().getType().name());
					newString = newString.replace("%trade1amount%", recipe.getItem1().getAmount()+"");
					newString = newString.replace("%location%", location);
					
					newLore.add(newString);
					
		}
		
		VisualData data = new VisualData(name, newLore);
		
		return data;
		
	}
	
	
	public VisualData getSelectionWithTwoItem(TradingRecipe recipe, Shopkeeper shop) {
		
		String location = shop.getX() + ", " + shop.getY() + ", " + shop.getZ();
		
		String name = MessageUtils.translateAlternateColorCodes(config.getString("Visuals.Selection.Name")
				.replace("%item%", recipe.getResultItem().getType().name())
				.replace("%trade1%", recipe.getItem1().getType().name())
				.replace("%trade2%", recipe.getItem2().getType().name())
				.replace("%trade1amount%", recipe.getItem1().getAmount()+"")
				.replace("%trade2amount%", recipe.getItem2().getAmount()+"")
				.replace("%location%", location));
		
		List<String> lore = ComponentBuilder.createLore(config.getStringList("Visuals.Selection.TwoItemTrade.Lore"));
		
		List<String> newLore = new ArrayList<String>();
		
		
		//two items
		for(int i = 0; i < lore.size(); i++) {
					String newString = lore.get(i).replace("%item%", recipe.getResultItem().getType().name());
					newString = newString.replace("%trade1%", recipe.getItem1().getType().name());
					newString = newString.replace("%trade2%", recipe.getItem2().getType().name());
					newString = newString.replace("%trade1amount%", recipe.getItem1().getAmount()+"");
					newString = newString.replace("%trade2amount%", recipe.getItem2().getAmount()+"");
					newString = newString.replace("%location%", location);
					
					newLore.add(newString);
					
		}
		
		VisualData data = new VisualData(name, newLore);
		
		return data;
		
		
	}

	
	public Visuals() {
		
	}

	public static Visuals instance() {
		return instance;
	}
	
	public static void reInitialize() {
		instance = new Visuals();
	}
	
	
}
