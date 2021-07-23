package com.keepers.data;

import org.bukkit.configuration.file.FileConfiguration;

import com.keepers.main.ShopKeepers;
import com.keepers.utils.MessageUtils;

public class GenericMessages {

	private static GenericMessages instance;
	
	public FileConfiguration messages = ShopKeepers.getInstance().configManager.getConfig("messages").configuration;

	public final String space = "\n" + "\n";
	
	public final String PREFIX = MessageUtils.translateAlternateColorCodes(messages.getString("Messages.PREFIX"));
	public final String INVALID_SYNTEX = MessageUtils.translateAlternateColorCodes(messages.getString("Messages.INVALID_SYNTEX")).replace("%prefix%", PREFIX);
	public final String ERROR = MessageUtils.translateAlternateColorCodes(messages.getString("Messages.ERROR")).replace("%prefix%", PREFIX);
	public final String INAVLID_ENTITY = MessageUtils.translateAlternateColorCodes(messages.getString("Messages.INAVLID_ENTITY")).replace("%prefix%", PREFIX);
	public final String INAVLID_PERMISSION = MessageUtils.translateAlternateColorCodes(messages.getString("Messages.INAVLID_PERMISSION")).replace("%prefix%", PREFIX);

	public final String SELF_CHUNK_FOUND = messages.getString("Detection.SELF_CHUNK_FOUND");
	public final String SLEF_CHUNK_EMPTY = messages.getString("Detection.SLEF_CHUNK_EMPTY");
	public final String NEAR_CHUNK_FOUND = messages.getString("Detection.NEAR_CHUNK_FOUND");
	public final String NEAR_CHUNK_EMPTY = messages.getString("Detection.NEAR_CHUNK_EMPTY");
	
	public GenericMessages() {
		
	}

	public static GenericMessages instance() {
		return instance;
	}
	
	public static void reInitialize() {
		instance = new GenericMessages();
	}
	
	
}
