package com.keepers.main;
import java.io.File;

import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import com.keepers.config.ConfigManager;
import com.keepers.cooldown.CooldownManager;
import com.keepers.cooldown.CooldownTick;
import com.keepers.cooldown.Cooldowns;
import com.keepers.data.GenericMessages;
import com.keepers.data.Permissions;
import com.keepers.data.Visuals;
import com.keepers.events.EventHandler;
import com.nisovin.shopkeepers.api.ShopkeepersPlugin;


public class ShopKeepers extends JavaPlugin implements Listener{


    public static ShopKeepers instance;
    public com.keepers.commands.CommandManager CommandManager;
    public ConfigManager configManager;
    public EventHandler eventHandler;
    public CooldownManager cooldownManager;
    public CooldownTick cooldownTick;
    public ShopHook shopHook;
	public File ParentFolder;
	
	@Override
	public void onEnable(){
		
		ParentFolder = getDataFolder();
	    instance = this;
		
	    this.shopHook = new ShopHook();
	    this.shopHook.onEnable(ShopkeepersPlugin.getInstance());
	    
	    configManager = new ConfigManager();
	    configManager.setup(this);
	    
	    this.reload();
	    
	    eventHandler = new EventHandler();
	    eventHandler.setup();
	    
	    this.cooldownManager = new CooldownManager();

	    this.cooldownTick = new CooldownTick();
	    this.cooldownTick.schedule();
	    
	    this.CommandManager = new com.keepers.commands.CommandManager();
	    this.CommandManager.setup(this);
	    
	    
	}
	
	
	@Override
	public void onDisable(){
		this.shopHook.onDisable();
		this.configManager.dispose();
		this.eventHandler = null;
		HandlerList.getRegisteredListeners(instance);
	}

	public void reload() {
	    GenericMessages.reInitialize();
	    Permissions.reInitialize();
	    Cooldowns.reInitialize();
	    Visuals.reInitialize();
	}
		
	

	public static ShopKeepers getInstance() {
		return instance;
	}
		
	
}
