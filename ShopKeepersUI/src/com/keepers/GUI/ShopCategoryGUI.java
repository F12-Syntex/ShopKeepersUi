package com.keepers.GUI;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.keepers.data.Permissions;
import com.keepers.data.VisualData;
import com.keepers.data.Visuals;
import com.keepers.main.ShopHook;
import com.keepers.main.ShopKeepers;
import com.keepers.utils.ComponentBuilder;
import com.keepers.utils.MessageUtils;
import com.keepers.utils.ShopUtils;

public class ShopCategoryGUI extends PagedGUI {

	private ShopHook shopHook = ShopKeepers.getInstance().shopHook;
	
	public ShopCategoryGUI(Player player) {
		super(player);
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

		Map<Material, List<ShopData>> catogaries = new HashMap<Material, List<ShopData>>();
		
		ShopUtils.get().forEach(shop -> {
		
			shop.getTradingRecipes(this.player).forEach(o -> {
				
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
		

		catogaries.keySet().forEach(key -> {
			
			VisualData category = Visuals.instance().getCategory(catogaries.get(key), key);
			
			ItemStack item = new ItemStack(key);
		
			ItemMeta meta = item.getItemMeta();
			meta.setDisplayName(MessageUtils.translateAlternateColorCodes(category.getName()));
			
			meta.setLore(ComponentBuilder.createLore(category.getLore()));
			
			item.setItemMeta(meta);
			
			items.add(new PagedItem(item, () -> {
				this.player.closeInventory();
				PagedGUI gui = new ShopItemGUI(player, catogaries.get(key));
				gui.open();
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
		// TODO Auto-generated method stub
		return new ArrayList<SpecialItem>();
	}

}
