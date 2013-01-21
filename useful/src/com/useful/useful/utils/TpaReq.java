package com.useful.useful.utils;

import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import com.useful.useful.useful;

public class TpaReq {
private Plugin plugin = useful.plugin;
private String Requester;
private String Accepter;
private boolean tpa;
long reqTime;
public TpaReq(String pRequester, String pAccepter, long pReqTimeMillis, boolean tpa){
	this.Requester = pRequester;
	this.Accepter = pAccepter;
	this.reqTime = pReqTimeMillis;
	this.tpa = tpa;
}
public String getRequester(){
	if(this.Requester == null){
		return null;
	}
	return this.Requester;
}
public String getAccepter(){
	if(this.Accepter == null){
		return null;
	}
	return this.Accepter;
}
public boolean getTpa(){
	return this.tpa;
}
public boolean getTimedOut(long currentTimeMillis){
	if(this.reqTime <= currentTimeMillis){
		return true;
	}
	else {
		return false;
	}
	
}
}
