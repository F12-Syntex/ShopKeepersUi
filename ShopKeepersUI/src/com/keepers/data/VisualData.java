package com.keepers.data;

import java.util.List;

public class VisualData {

	private String name;
	private List<String> lore;
	
	public VisualData(String name, List<String> lore) {
		this.name = name;
		this.lore = lore;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getLore() {
		return lore;
	}

	public void setLore(List<String> lore) {
		this.lore = lore;
	}
	
}
