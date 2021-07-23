package com.keepers.GUI;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import com.keepers.config.Messages;
import com.keepers.data.GenericMessages;
import com.keepers.main.ShopKeepers;
import com.keepers.utils.MessageUtils;

public abstract class GUI implements Listener{

	protected Player player;
	protected Inventory inv;
	protected Messages messages;
	
	public GUI(Player player) {
		this.player = player;
		this.inv = Bukkit.createInventory(this.player, size(), name());
		this.messages = ShopKeepers.getInstance().configManager.messages;
	}
	
	@EventHandler()
	public void onOpen(InventoryOpenEvent e) {
		if(e.getPlayer().getUniqueId().compareTo(this.player.getUniqueId()) != 0) return;
		if(!e.getView().getTitle().equals(this.name())) return;
		if(permission() != null) {
			if(!e.getPlayer().hasPermission(permission())) {
				MessageUtils.sendRawMessage(player, GenericMessages.instance().INAVLID_PERMISSION);
				e.setCancelled(true);
				return;
			}
		}
		onOpenInventory(e);
	}
	
	@EventHandler()
	public void onClose(InventoryCloseEvent e) {
		
		/*
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        StackTraceElement element = stackTrace[2];
        System.out.println("I was called by a method named: " + element.getMethodName());
        System.out.println("That method is in class: " + element.getClassName());
		*/
		
		if(e.getPlayer().getUniqueId().compareTo(this.player.getUniqueId()) != 0) return;
		if(!e.getView().getTitle().equals(this.name())) return;
		this.onCloseInventory(e);
		HandlerList.unregisterAll(this);
	}
	
	@EventHandler()
	public void onClick(InventoryClickEvent e) {
		if(e.getWhoClicked().getUniqueId().compareTo(this.player.getUniqueId()) != 0) return;
		if(e.getClickedInventory() == null) return;
		if(e.getClickedInventory().getType() == InventoryType.PLAYER) {
			e.setCancelled(true);
			return;
		}
		if(!e.getView().getTitle().equals(this.name())) return;
		e.setCancelled(!canTakeItems());
		if(e.getCurrentItem() == null) {
			return;
		}
		this.onClickInventory(e);
	}
	
	public abstract String name();
	public abstract String permission();
	
	public abstract int size();
	
	public abstract Sound sound();
	public abstract float soundLevel();
	public abstract boolean canTakeItems();
	
	public abstract void onClickInventory(InventoryClickEvent e);
	public abstract void onOpenInventory(InventoryOpenEvent e);
	public abstract void onCloseInventory(InventoryCloseEvent e);
	public abstract void Contents(Inventory inv);
	
	public void open() {
		this.player.closeInventory();
		ShopKeepers.instance.getServer().getPluginManager().registerEvents(this, ShopKeepers.instance);
		
		player.getWorld().playSound(player.getLocation(), sound(), soundLevel(), soundLevel());
	    this.Contents(inv);
	    
	    player.openInventory(inv);
	    
	    /*
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        StackTraceElement element = stackTrace[2];
        System.out.println("I was called by a method named: " + element.getMethodName());
        System.out.println("That method is in class: " + element.getClassName());
	    */
	    
	}
	
	public void addItem(int index, ItemStack item) {
		inv.setItem(index, item);
	}
	
	public void fillEmpty(ItemStack stack) {
		for(int i = 0; i < this.size(); i++) {
			if(this.inv.getItem(i) == null) {
				this.inv.setItem(i, stack);
			}
		}
	}
	
	
}
