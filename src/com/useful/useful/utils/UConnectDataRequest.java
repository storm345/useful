package com.useful.useful.utils;

import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;

public class UConnectDataRequest {
	private String type = null;
	private Object[] args = null;
	private CommandSender sender = null;
	private YamlConfiguration data = new YamlConfiguration();
	public UConnectDataRequest(String requestKey, Object[] args, CommandSender sender){
		this.type = requestKey;
		this.args = args;
		this.sender = sender;
	}
	public String getRequestKey(){
		return this.type;
	}
	public Object[] getArgs(){
		return this.args;
	}
	public void setArgs(Object[] newArgs){
		this.args = newArgs;
		return;
	}
    public void setData(YamlConfiguration newData){
    	this.data = newData;
    	return;
    }
    public YamlConfiguration getData(){
    	return this.data;
    }
    public CommandSender getSender(){
    	return this.sender;
    }
    public void setType(String type){
    	this.type = type;
    	return;
    }
}
