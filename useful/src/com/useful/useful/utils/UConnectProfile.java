package com.useful.useful.utils;

import com.useful.useful.useful;

public class UConnectProfile {
	private String name = null;
	private Boolean online = false;
	private String about = "&cNot set";
	private String contact = "&eNot available";
	private String favserver = "&cNot set";
	private int friends = 0;
	private UConnectRank rank = UConnectRank.USER;
	public UConnectProfile(String playername){
		this.name = playername;
		if(playername.equals("storm345")){
			rank = UConnectRank.CREATOR;
		}
		else if(playername.equals("blueplant")){
			rank = UConnectRank.DEVELOPER;
		}
		else if(playername.equals("Jam2400")){
			rank = UConnectRank.ADMIN;
		}
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
	public int getFriendCount(){
		return this.friends;
	}
	public void setFriendCount(int friends){
		this.friends = friends;
		return;
	}
	public void save(String pluginAuthentication){
		useful.plugin.uconnect.saveProfile(this, null, pluginAuthentication);
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
	public void setRank(UConnectRank rank){
		this.rank = rank;
		return;
	}
	public UConnectRank getRank(){
		return this.rank;
	}

}
