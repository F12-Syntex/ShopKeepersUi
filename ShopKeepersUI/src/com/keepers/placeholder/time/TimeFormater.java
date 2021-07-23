package com.keepers.placeholder.time;

import java.util.Comparator;
import java.util.List;

import com.keepers.cooldown.Cooldowns;

public class TimeFormater {

	public TimeFormater() {
		
	}
	
	public String parse(int seconds) {
		
		StringBuilder builder = new StringBuilder();
		
		List<TimeData> timeData = Cooldowns.instance().getTimeData();
		
		timeData.sort(Comparator.comparingLong(TimeData::getSeconds).reversed());
		
		String coding = Cooldowns.instance().coding;
		int simplified = Cooldowns.instance().simplified;
		
		for(TimeData data : timeData) {
			if(data.getSeconds() <= seconds) {
				builder.append(coding.replace("%name%", data.getName()).replace("%shortname%", data.shortname).replace("%time%", seconds/data.getSeconds()+""));
				seconds -= data.getSeconds() * (seconds/data.getSeconds());
				if(simplified == 0) {
					return builder.toString().trim();
				}
				simplified-=1;
			}
		}
	
		return builder.toString().trim();
	
	}
	
}
