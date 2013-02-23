package com.useful.useful.utils;

import com.useful.useful.useful;

public class UConnectProfile {
	private String name = null;
	private Boolean online = false;
	public UConnectProfile(String playername){
		this.name = playername;
	}
	public String getName(){
		return this.name;
	}
	public Boolean isOnline(){
		return this.online;
	}
	public void setOnline(Boolean isOnline){
		this.online = isOnline;
		return;
	}
	public void save(){
		useful.plugin.uconnect.saveProfile(this);
		return;
	}

}
