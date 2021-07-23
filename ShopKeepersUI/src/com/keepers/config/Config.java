package com.keepers.config;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import com.keepers.identification.ConfigID;

public abstract class Config {
	
	public FileConfiguration configuration;
	
	public File configFile;
	
	public int version = 1;
	
	String name = "";
	
    public Config() {

    }
    
	public abstract int version();
	
	public abstract String folder();

    public abstract String info();
    
    public abstract String name();
    
    public abstract boolean exception();
    public abstract boolean saveData();
    
    public abstract ConfigID indentification();

    public abstract FileConfiguration getInstance();
    
    public void setup(Plugin plugin) {
    	if(folder() == null || folder().isEmpty()) {
    		
    		configFile = new File(plugin.getDataFolder().getAbsolutePath(), name() + ".yml");	
    	
    		if(!configFile.exists()) {
    			plugin.saveResource(name() + ".yml", false);
    			this.configuration = YamlConfiguration.loadConfiguration(configFile);
    		} else {
    			this.configuration = YamlConfiguration.loadConfiguration(configFile);
    		}
    		
    	}else {
    		
    		File folderPath = new File(plugin.getDataFolder().getAbsolutePath(), folder());
    		folderPath.mkdirs();
    		
    		configFile = new File(folderPath, name() + ".yml");
    	
    		if(!configFile.exists()) {
    			plugin.saveResource(folder() + "\\" + name() + ".yml", false);
    			this.configuration = YamlConfiguration.loadConfiguration(configFile);
    		} else {
    			this.configuration = YamlConfiguration.loadConfiguration(configFile);
    		}
    	}
    	
	}
    
    public void save() {
		try {
			getConfig().save(configFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
	public FileConfiguration getConfig() {
		return configuration;
	}
	
    public void reload() throws IOException{
    	this.configuration = YamlConfiguration.loadConfiguration(configFile);
    }
 
	
	
}
