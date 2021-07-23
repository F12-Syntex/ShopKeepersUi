
package com.keepers.commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.keepers.main.ShopKeepers;
import com.keepers.utils.MessageUtils;

public class Reload extends SubCommand {

    @Override
    public void onCommand(Player player, String[] args) {
    	ShopKeepers.getInstance().configManager.reload();
    	player.sendMessage(messages.PREFIX + " " + MessageUtils.translateAlternateColorCodes("&6reloaded!"));
    }

    @Override

    public String name() {
        return "reload";
    }

    @Override
    public String info() {
        return "reloads the plugin.";
    }

    @Override
    public String[] aliases() {
        return new String[0];
    }

	@Override
	public String permission() {
		return permissions.ADMIN;	
	}
	
	@Override
	public AutoComplete autoComplete(CommandSender sender) {
		AutoComplete tabCompleter = new AutoComplete();
		return tabCompleter;
	}
	

}