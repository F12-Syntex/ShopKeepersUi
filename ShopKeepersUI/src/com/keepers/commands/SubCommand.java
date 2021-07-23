package com.keepers.commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.keepers.data.ConfigData;

public abstract class SubCommand extends ConfigData {
	
    public SubCommand() {

    }

    public abstract void onCommand(Player player, String[] args);

    public abstract String name();
   
    public abstract String permission();

    public abstract String info();

    public abstract String[] aliases();
    
    public abstract AutoComplete autoComplete(CommandSender sender);

}
