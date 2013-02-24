package com.useful.useful.utils;

import com.useful.useful.useful;

public class UConnectProfile {
	private String name = null;
	private Boolean online = false;
	private String about = "&cNot set";
	private String contact = "&eNot available";
	private String favserver = "&cNot set";
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
		useful.plugin.uconnect.saveProfile(this, null);
		return;
	}
	public void setAbout(String about){
		this.about = about;
		return;
	}
	public String getAbout(){
		return this.about;
	}
	public String getContactInfo(){
		return this.contact;
	}
	public void setContactInfo(String info){
		this.contact = info;
		return;
	}
	public void setFavServer(String servername){
		this.favserver = servername;
		return;
	}
	public String getFavServer(){
		return this.favserver;
	}

}
