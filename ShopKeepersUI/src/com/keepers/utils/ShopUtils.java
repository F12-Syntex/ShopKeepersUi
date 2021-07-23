package com.keepers.utils;

import java.util.Collection;

import com.keepers.main.ShopKeepers;
import com.nisovin.shopkeepers.api.shopkeeper.Shopkeeper;

public class ShopUtils {

	
	private static Collection<? extends Shopkeeper> keepers;
	
	public static Collection<? extends Shopkeeper> get() {
		
		if(ShopUtils.keepers == null || ShopKeepers.getInstance().shopHook.getPlugin().getShopkeeperRegistry().getAllShopkeepers().size() != ShopUtils.keepers.size()) {
			ShopUtils.keepers = ShopKeepers.getInstance().shopHook.getPlugin().getShopkeeperRegistry().getAllShopkeepers();
		}
		
		return ShopUtils.keepers;
		
	}
	
}
