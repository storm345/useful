package com.useful.useful.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

import com.useful.useful.useful;

public class UConnect {
private YamlConfiguration main = new YamlConfiguration();
private YamlConfiguration profiles = new YamlConfiguration();
public final HashMap<String, Boolean> tasks = new HashMap<String, Boolean>();
private useful plugin = useful.plugin;
private File cache = null;
public UConnect(){
	this.load();
	if(!main.contains("uconnect.create")){
		main.set("uconnect.create", true);
	}
    this.save();
    this.loadProfiles();
    if(!profiles.contains("uconnect.create")){
		profiles.set("uconnect.create", true);
	}
    if(!profiles.contains("profiles.create")){
		profiles.set("profiles.create", true);
	}
    this.saveProfiles();
}
public void load(){
	try {
		this.cache = new File("uConnectDataCache.txt");
		this.cache.createNewFile();
	} catch (IOException e1) {
		return;
	}
	String uuid = UniqueString.generate();
	this.tasks.put(uuid, false);
	File checkFile = uConnectConnect.getFile("/main.yml", this.cache, uuid);
	while(!this.tasks.get(uuid)){
		//useful.plugin.colLogger.info("main: " + this.tasks.get(uuid));
	}
	this.tasks.remove(uuid);
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
	String uuid = UniqueString.generate();
	this.tasks.put(uuid, false);
	uConnectConnect.uploadFile(this.cache, "/main.yml", uuid);
	while(!this.tasks.get(uuid)){
		
	}
	this.tasks.remove(uuid);
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
public void loadProfiles(){
	try {
		this.cache = File.createTempFile("uConnectDataCache", ".txt");
	} catch (IOException e1) {
		return;
	}
	String uuid = UniqueString.generate();
	this.tasks.put(uuid, false);
	File checkFile = uConnectConnect.getFile("/profiles.yml", this.cache, uuid);
	while(!this.tasks.get(uuid)){
		
	}
	this.tasks.remove(uuid);
	if(checkFile == null){
		this.cache.delete();
		return;
	}
	try {
		this.profiles.load(this.cache);
	} catch (Exception e) {
		this.profiles = new YamlConfiguration();
		this.cache.delete();
	}
	this.cache.delete();
	return;
}
public void saveProfiles(){
	try {
		this.cache = File.createTempFile("uConnectDataCache", ".txt");
	} catch (IOException e1) {
		return;
	}
	try {
		this.profiles.save(cache);
	} catch (IOException e) {
		return;
	}
	String uuid = UniqueString.generate();
	this.tasks.put(uuid, false);
	uConnectConnect.uploadFile(this.cache, "/profiles.yml", uuid);
	while(!this.tasks.get(uuid)){
		
	}
	this.tasks.remove(uuid);
	this.cache.delete();
	return;
}
public void updateProfiles(){
	loadProfiles();
	saveProfiles();
}
public void saveProfile(UConnectProfile profile){
	String name = profile.getName();
	loadProfiles();
	this.profiles.set("profiles." + name + ".online", profile.isOnline());
	saveProfiles();
}
public UConnectProfile loadProfile(String pname){
	loadProfiles();
	ConfigurationSection profiles = this.profiles.getConfigurationSection("profiles");
	Set<String> names = profiles.getKeys(false);
	Boolean contains = false;
	for(String name:names){
		if(name == pname){
			contains = true;
		}
	}
	saveProfiles();
	if(contains){
	UConnectProfile profile = new UConnectProfile(pname);
	if(this.profiles.contains("profiles." + pname + ".online")){
		profile.setOnline(this.profiles.getBoolean("profiles."+pname+".online"));
	}
	return profile;	
	}
	else{
		return new UConnectProfile(pname);
	}
}
public int MessageCount(String pname){
	if(this.main.contains("messaging."+pname)){
		return this.main.getStringList("messaging."+pname).size();
	}
	else{
		return 0;
	}
}

}
