package com.keepers.GUI;

import com.nisovin.shopkeepers.api.shopkeeper.Shopkeeper;
import com.nisovin.shopkeepers.api.shopkeeper.TradingRecipe;

public class ShopData {

	private TradingRecipe tradingRecipe;
	private Shopkeeper shop;
	
	public ShopData(Shopkeeper shop, TradingRecipe tradingRecipe) {
		this.tradingRecipe = tradingRecipe;
		this.shop = shop;
	}

	public TradingRecipe getTradingRecipe() {
		return tradingRecipe;
	}

	public void setTradingRecipe(TradingRecipe tradingRecipe) {
		this.tradingRecipe = tradingRecipe;
	}

	public Shopkeeper getShop() {
		return shop;
	}

	public void setShop(Shopkeeper shop) {
		this.shop = shop;
	}

}
