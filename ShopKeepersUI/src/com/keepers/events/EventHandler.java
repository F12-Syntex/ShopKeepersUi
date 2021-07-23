package com.keepers.events;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.plugin.Plugin;

import com.keepers.main.ShopKeepers;

public class EventHandler {

    public static List<SubEvent> events = new ArrayList<SubEvent>();
	
    private Plugin plugin = ShopKeepers.instance;
    
	public void setup() {
		events.add(new InputHandler());
		events.forEach(i -> plugin.getServer().getPluginManager().registerEvents(i, plugin));
	}
	
}
