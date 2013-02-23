package com.useful.useful.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

import com.useful.useful.useful;

public class UConnect {
private YamlConfiguration main = new YamlConfiguration();
private useful plugin = useful.plugin;
private File cache = null;
public UConnect(){
	this.load();
	if(!main.contains("uconnect.create")){
		main.set("uconnect.create", true);
    this.save();
	}
}
public void load(){
	try {
		this.cache = File.createTempFile("uConnectDataCache", ".txt");
	} catch (IOException e1) {
		return;
	}
	File checkFile = uConnectConnect.getFile("/main.yml", this.cache);
	if(checkFile == null){
		this.cache.delete();
		return;
	}
	try {
		this.main.load(this.cache);
	} catch (Exception e) {
		this.main = new YamlConfiguration();
		this.cache.delete();
	}
	this.cache.delete();
	return;
}
public void save(){
	try {
		this.cache = File.createTempFile("uConnectDataCache", ".txt");
	} catch (IOException e1) {
		return;
	}
	try {
		this.main.save(cache);
	} catch (IOException e) {
		return;
	}
	uConnectConnect.uploadFile(this.cache, "/main.yml");
	this.cache.delete();
	return;
}
public void message(UConnectProfile to, UConnectProfile from, String msg){
	String toName = to.getName();
	String fromName = from.getName();
	this.load();
	//messaging.toname
	List<String> inbox = new ArrayList<String>();
	if(this.main.contains("messaging." + toName)){
		inbox = this.main.getStringList("messaging." + toName);
	}
	Date cal = Calendar.getInstance().getTime();
	String time = new SimpleDateFormat("dd-MM : HH:mm:ss").format(cal);
	inbox.add("&r&i["+time+"]&r&3[From]&a" + fromName + ": &6" + msg);
	this.main.set("messaging." + toName, inbox);
	this.save();
}
public void clearMessages(UConnectProfile player){
	String name = player.getName();
	this.load();
	if(this.main.contains("messaging." + name)){
		this.main.set("messaging." + name, null);
	}
	this.save();
}
public List<String> getMessages(UConnectProfile player){
	this.update();
	String name = player.getName();
	if(this.main.contains("messaging." + name)){
		return this.main.getStringList("messaging."+name);
	}
	else{
		List<String> result = new ArrayList<String>();
		//result.add("&aNo new messages");
		return result;
	}
}
public void update(){
	this.load();
	this.save(); //Before using save in a method ALWAYS use load for security
}
}
