package com.tower.core;

import com.tower.resource.PropertiesUtil;

public class Waves {
	
	private PropertiesUtil proer;
	private String waves;
	public Waves(int wave){
		
		waves=proer.getProperty(wave);
	}
	public String getWaves() {
		return waves;
	}
	public void setWaves(String waves) {
		this.waves = waves.substring(1);
	}
	public PropertiesUtil getProer() {
		return proer;
	}
	public void setProer(PropertiesUtil proer) {
		this.proer = proer;
	}
}
