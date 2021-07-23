
package com.keepers.commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.keepers.GUI.PagedGUI;
import com.keepers.GUI.ShopCategoryGUI;

public class Open extends SubCommand {

    @Override
    public void onCommand(Player player, String[] args) {
    	
	    PagedGUI Interface = new ShopCategoryGUI(player);
	    Interface.open();
	
    }

    @Override

    public String name() {
        return "open";
    }

    @Override
    public String info() {
        return "Opens the shopKeepers GUI";
    }

    @Override
    public String[] aliases() {
        return new String[0];
    }

	@Override
	public String permission() {
		return permissions.DEFAULT;	
	}
	
	@Override
	public AutoComplete autoComplete(CommandSender sender) {
		AutoComplete tabCompleter = new AutoComplete();
		return tabCompleter;
	}

}