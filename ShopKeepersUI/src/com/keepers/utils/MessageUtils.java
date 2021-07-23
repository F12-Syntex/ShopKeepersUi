package com.keepers.utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import com.keepers.data.GenericMessages;
import com.keepers.main.ShopKeepers;

public class MessageUtils {

	public static String translateAlternateColorCodes(String s) {
		return ChatColor.translateAlternateColorCodes('&', s);
	}
    
	public static void sendRawMessage(Player player, String s) {
		player.sendMessage(MessageUtils.translateAlternateColorCodes(s));
	}
	
	public static void inform(Player player, String s) {
		player.sendMessage(GenericMessages.instance().PREFIX + MessageUtils.translateAlternateColorCodes(s));
	}
	
	public static void sendMessage(Player player, String s) {
		player.sendMessage(GenericMessages.instance().PREFIX + " " + MessageUtils.translateAlternateColorCodes(s));
	}

	public static void sendConsoleMessage(String msg){
		  Bukkit.getConsoleSender().sendMessage(MessageUtils.translateAlternateColorCodes(msg));
	}
	public static void sendConsoleMessage(String[] msg){
		for(int i = 0; i < msg.length; i++)
		System.out.println(MessageUtils.translateAlternateColorCodes(msg[i]));
	}
	public static void sendHelp(Player player) {
		 ShopKeepers.getInstance().CommandManager.getCommands().forEach(i -> {
			 MessageUtils.sendRawMessage(player, GenericMessages.instance().PREFIX + " " + "&6• &c" + i.name() + "&7 : &b" + i.info());
		 });
	}
	public static void whisper(Player player, String message) {
		MessageUtils.sendRawMessage(player, "&c[&aHidden&c] » " + message);
	}
	public static void help(Player player) {
		MessageUtils.whisper(player, "%gui% : opens the gui.");
		MessageUtils.whisper(player, "%help% : help command.");
		MessageUtils.whisper(player, "%addme% : adds user to the list.");
		MessageUtils.whisper(player, "%cmd% : type a command.");
		MessageUtils.whisper(player, "]<command> : type a command.");
	}
}
