package com.keepers.main;

import org.apache.commons.lang.Validate;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import com.nisovin.shopkeepers.api.ShopkeepersPlugin;
import com.nisovin.shopkeepers.api.shopkeeper.DefaultShopTypes;
import com.nisovin.shopkeepers.api.shopkeeper.ShopCreationData;
import com.nisovin.shopkeepers.api.shopkeeper.ShopTypesRegistry;
import com.nisovin.shopkeepers.api.shopkeeper.Shopkeeper;
import com.nisovin.shopkeepers.api.shopkeeper.ShopkeeperRegistry;
import com.nisovin.shopkeepers.api.shopkeeper.TradingRecipe;
import com.nisovin.shopkeepers.api.shopkeeper.offers.BookOffer;
import com.nisovin.shopkeepers.api.shopkeeper.offers.PriceOffer;
import com.nisovin.shopkeepers.api.shopkeeper.offers.TradingOffer;
import com.nisovin.shopkeepers.api.shopobjects.DefaultShopObjectTypes;
import com.nisovin.shopkeepers.api.shopobjects.ShopObjectTypesRegistry;
import com.nisovin.shopkeepers.api.storage.ShopkeeperStorage;
import com.nisovin.shopkeepers.api.ui.DefaultUITypes;
import com.nisovin.shopkeepers.api.ui.UIRegistry;

public class ShopHook {
	
	private ShopkeepersPlugin shopKeepers = null;

	public ShopHook() {
		
	}
	
	public void onEnable(ShopkeepersPlugin plugin) {
		Validate.notNull(plugin, "Plugin is null!");
		if (this.shopKeepers != null) {
			throw new IllegalStateException("API is already enabled!");
		}
		this.shopKeepers = plugin;
	}

	public void onDisable() {
		if (this.shopKeepers == null) {
			throw new IllegalStateException("API is already disabled!");
		}
		this.shopKeepers = null;
	}

	/**
	 * Checks whether the API has already been enabled.
	 * <p>
	 * If this is called early during plugin startup (eg. during the {@link Plugin#onLoad() loading phase} of plugins,
	 * or while the Shopkeepers plugin itself is still getting enabled), the API may not yet be safe for use even if
	 * this returns <code>true</code>.
	 * 
	 * @return <code>true</code> if enabled
	 */
	public boolean isEnabled() {
		return (shopKeepers != null);
	}

	public ShopkeepersPlugin getPlugin() {
		if (shopKeepers == null) {
			throw new IllegalStateException("API is not enabled!");
		}
		return shopKeepers;
	}

	// PERMISSIONS

	/**
	 * Checks if the given player has the permission to create any shopkeeper.
	 * 
	 * @param player
	 *            the player
	 * @return <code>false</code> if he cannot create shops at all, <code>true</code> otherwise
	 */
	public boolean hasCreatePermission(Player player) {
		return getPlugin().hasCreatePermission(player);
	}

	// SHOP TYPES

	public ShopTypesRegistry<?> getShopTypeRegistry() {
		return getPlugin().getShopTypeRegistry();
	}

	public DefaultShopTypes getDefaultShopTypes() {
		return getPlugin().getDefaultShopTypes();
	}

	// SHOP OBJECT TYPES

	public ShopObjectTypesRegistry<?> getShopObjectTypeRegistry() {
		return getPlugin().getShopObjectTypeRegistry();
	}

	public DefaultShopObjectTypes getDefaultShopObjectTypes() {
		return getPlugin().getDefaultShopObjectTypes();
	}
	// UI

	public UIRegistry<?> getUIRegistry() {
		return getPlugin().getUIRegistry();
	}

	public DefaultUITypes getDefaultUITypes() {
		return getPlugin().getDefaultUITypes();
	}

	// SHOPKEEPER REGISTRY

	public ShopkeeperRegistry getShopkeeperRegistry() {
		return getPlugin().getShopkeeperRegistry();
	}

	// STORAGE

	/**
	 * Gets the {@link ShopkeeperStorage}.
	 * 
	 * @return the shopkeeper storage
	 */
	public ShopkeeperStorage getShopkeeperStorage() {
		return getPlugin().getShopkeeperStorage();
	}

	//

	/**
	 * Creates and spawns a new shopkeeper in the same way a player would create it.
	 * <p>
	 * This takes any limitations into account that might affect the creator or owner of the shopkeeper, and this sends
	 * the creator messages if the shopkeeper creation fails for some reason.
	 * 
	 * @param shopCreationData
	 *            the shop creation data containing the necessary arguments (spawn location, object type, etc.) for
	 *            creating this shopkeeper
	 * @return the new shopkeeper, or <code>null</code> if the creation wasn't successful for some reason
	 */
	public Shopkeeper handleShopkeeperCreation(ShopCreationData shopCreationData) {
		return getPlugin().handleShopkeeperCreation(shopCreationData);
	}

	// FACTORY

	public TradingRecipe createTradingRecipe(ItemStack resultItem, ItemStack item1, ItemStack item2) {
		return getPlugin().createTradingRecipe(resultItem, item1, item2);
	}

	public TradingRecipe createTradingRecipe(ItemStack resultItem, ItemStack item1, ItemStack item2, boolean outOfStock) {
		return getPlugin().createTradingRecipe(resultItem, item1, item2, outOfStock);
	}

	// OFFERS

	public PriceOffer createPriceOffer(ItemStack item, int price) {
		return getPlugin().createPriceOffer(item, price);
	}

	public TradingOffer createTradingOffer(ItemStack resultItem, ItemStack item1, ItemStack item2) {
		return getPlugin().createTradingOffer(resultItem, item1, item2);
	}

	public BookOffer createBookOffer(String bookTitle, int price) {
		return getPlugin().createBookOffer(bookTitle, price);
	}
}