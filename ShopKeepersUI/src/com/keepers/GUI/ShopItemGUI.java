package com.keepers.GUI;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.keepers.cooldown.CooldownRunnable;
import com.keepers.cooldown.Cooldowns;
import com.keepers.data.Permissions;
import com.keepers.data.VisualData;
import com.keepers.data.Visuals;
import com.keepers.main.ShopHook;
import com.keepers.main.ShopKeepers;
import com.keepers.tags.TagFactory;
import com.keepers.utils.MessageUtils;
import com.nisovin.shopkeepers.api.shopkeeper.Shopkeeper;
import com.nisovin.shopkeepers.api.shopkeeper.TradingRecipe;

public class ShopItemGUI extends PagedGUI {

	private ShopHook shopHook = ShopKeepers.getInstance().shopHook;
	
	private List<ShopData> trading;
	
	public ShopItemGUI(Player player, List<ShopData> trading) {
		super(player);
		this.trading = trading;
	}


	@Override
	public String name() {
		// TODO Auto-generated method stub
		return MessageUtils.translateAlternateColorCodes("Shop Keepers");
	}

	@Override
	public String permission() {
		// TODO Auto-generated method stub
		return Permissions.instance().DEFAULT;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 54;
	}

	@Override
	public Sound sound() {
		// TODO Auto-generated method stub
		return Sound.BLOCK_TRIPWIRE_CLICK_ON;
	}

	@Override
	public float soundLevel() {
		// TODO Auto-generated method stub
		return 1f;
	}

	@Override
	public boolean canTakeItems() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void onClickInventory(InventoryClickEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onOpenInventory(InventoryOpenEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onCloseInventory(InventoryCloseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public List<PagedItem> Contents() {

		List<PagedItem> items = new ArrayList<PagedItem>();

		this.trading.forEach(key -> {
			
			TradingRecipe recipe = key.getTradingRecipe();
			Shopkeeper shop = key.getShop();
			
			ItemStack item = new ItemStack(recipe.getResultItem());
		
			ItemMeta meta = item.getItemMeta();
			
			if(recipe.getItem2() == null) {
				VisualData category = Visuals.instance().getSelectionWithOneItem(recipe, shop);
				
				if(recipe.getResultItem().getItemMeta().hasDisplayName()) {
					meta.setDisplayName(recipe.getResultItem().getItemMeta().getDisplayName());
				}else {
					meta.setDisplayName(category.getName());
					
				}
				List<String> newLore = new ArrayList<String>();
				
				if(recipe.getResultItem().getItemMeta().hasLore()) {
					newLore = recipe.getResultItem().getItemMeta().getLore();
				}
				if(category.getLore() != null) {
					for(String i : category.getLore()) {
						newLore.add(i);
					}
				}
				
				meta.setLore(newLore);
				
				
				
			}else {
				VisualData category = Visuals.instance().getSelectionWithTwoItem(recipe, shop);
				if(recipe.getResultItem().getItemMeta().hasDisplayName()) {
					meta.setDisplayName(recipe.getResultItem().getItemMeta().getDisplayName());
				}else {
					meta.setDisplayName(category.getName());
					
				}
				List<String> newLore = new ArrayList<String>();
				
				if(recipe.getResultItem().getItemMeta().hasLore()) {
					newLore = recipe.getResultItem().getItemMeta().getLore();
				}
				if(category.getLore() != null) {
					for(String i : category.getLore()) {
						newLore.add(i);
					}
				}
				
				meta.setLore(newLore);
			}
			
			item.setItemMeta(meta);
			
			items.add(new PagedItem(item, () -> {
				
				CooldownRunnable exe = new CooldownRunnable(Cooldowns.instance().cooldownTimer, () -> {
					player.teleport(shop.getLocation());
				
				}) {
					
				@Override
				public void onTick() {
				
				}
				
				};
				
				TagFactory tagHelper = TagFactory.instance(Cooldowns.instance().teleportMessage);
                
            	tagHelper.setCooldown(Cooldowns.instance().cooldownTimer);
            	
            	MessageUtils.sendMessage(player, tagHelper.parse());
				
				ShopKeepers.getInstance().cooldownManager.getRunnables().add(exe);
				
				player.closeInventory();
				
			}));
			
		});
		
		return items;
	
	}


	public ShopHook getShopHook() {
		return shopHook;
	}

	public void setShopHook(ShopHook shopHook) {
		this.shopHook = shopHook;
	}


	@Override
	public List<SpecialItem> SpecialContents() {
		
		List<SpecialItem> item = new ArrayList<SpecialItem>();
		
		ItemStack back = new ItemStack(Material.ORANGE_STAINED_GLASS_PANE);
		ItemMeta meta = back.getItemMeta();
		
		meta.setDisplayName(MessageUtils.translateAlternateColorCodes("&6Back"));
		back.setItemMeta(meta);
		
		SpecialItem special = new SpecialItem(back, () -> {
			
			player.closeInventory();
			PagedGUI gui = new ShopCategoryGUI(player);
			gui.open();
			
		}, 49);
		
		item.add(special);
		
		return item;
	}

}
