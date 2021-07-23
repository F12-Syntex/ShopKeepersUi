package com.keepers.config;

import org.bukkit.configuration.file.FileConfiguration;

import com.keepers.identification.ConfigID;

public class Visuals extends Config{

	@Override
	public int version() {
		// TODO Auto-generated method stub
		return 2;
	}

	@Override
	public String info() {
		// TODO Auto-generated method stub
		return "visuals config";
	}

	@Override
	public String name() {
		// TODO Auto-generated method stub
		return "visuals";
	}

	@Override
	public ConfigID indentification() {
		// TODO Auto-generated method stub
		return ConfigID.VISUALS;
	}

	@Override
	public FileConfiguration getInstance() {
		// TODO Auto-generated method stub
		return configuration;
	}

	@Override
	public boolean exception() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String folder() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean saveData() {
		// TODO Auto-generated method stub
		return false;
	}

}
