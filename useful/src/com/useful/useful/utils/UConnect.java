package com.useful.useful.utils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;

import com.useful.useful.useful;

public class UConnect {
public YamlConfiguration main = new YamlConfiguration();
public YamlConfiguration profiles = new YamlConfiguration();
public final HashMap<String, Boolean> tasks = new HashMap<String, Boolean>();
private useful plugin = useful.plugin;
private File cache = null;
public UConnect(){
	try {
		this.cache = new File(plugin.getDataFolder().getAbsolutePath() + File.separator + "uConnect" + File.separator + "uConnectDataCache.txt");
		this.cache.createNewFile();
	} catch (IOException e) {
		e.printStackTrace();
	}
	/*
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
    */
}
public void load(UConnectDataRequest request){
	try {
		this.cache = new File(plugin.getDataFolder().getAbsolutePath() + File.separator + "uConnect" + File.separator + "uConnectDataCache.txt");
		this.cache.createNewFile();
	} catch (IOException e1) {
		return;
	}
	String uuid = UniqueString.generate();
	this.tasks.put(uuid, false);
	uConnectConnect.getFile("/main.yml", uuid, request);
	/*
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
	if(!main.contains("uconnect.create")){
		main.set("uconnect.create", true);
	}
	*/
	return;
}
public void save(){
	try {
		this.cache = new File(plugin.getDataFolder().getAbsolutePath() + File.separator + "uConnect" + File.separator + "uConnectDataCache.txt");
		this.cache.createNewFile();
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
	uConnectConnect.uploadYaml(this.main, "/main.yml", uuid);
	return;
}
public void message(UConnectProfile to, UConnectProfile from, String msg, CommandSender sender){
	this.cache = new File(plugin.getDataFolder().getAbsolutePath() + File.separator + "uConnect" + File.separator + "uConnectDataCache.txt");
	try {
		this.cache.createNewFile();
	} catch (IOException e) {
		e.printStackTrace();
	}
	String toName = to.getName();
	String fromName = from.getName();
	List<String> args = new ArrayList<String>();
	args.add(toName);
	args.add(fromName);
	args.add(msg);
	UConnectDataRequest request = new UConnectDataRequest("msg", args.toArray(), sender);
	@SuppressWarnings("unused")
	UConnectRequestScheduler exec = new UConnectRequestScheduler("main", request, 5000, 5);
	//this.load(request);
	//messaging.toname
	/*
	List<String> inbox = new ArrayList<String>();
	if(this.main.contains("messaging." + toName)){
		inbox = this.main.getStringList("messaging." + toName);
	}
	Date cal = Calendar.getInstance().getTime();
	String time = new SimpleDateFormat("dd-MM : HH:mm:ss").format(cal);
	inbox.add("&r&i["+time+"]&r&3[From]&a" + fromName + ": &6" + msg);
	this.main.set("messaging." + toName, inbox);
	this.save();
	*/
}
public void clearMessages(UConnectProfile player, CommandSender sender){
	this.cache = new File(plugin.getDataFolder().getAbsolutePath() + File.separator + "uConnect" + File.separator + "uConnectDataCache.txt");
	try {
		this.cache.createNewFile();
	} catch (IOException e) {
		e.printStackTrace();
	}
	String name = player.getName();
	List<String> args = new ArrayList<String>();
	args.add(name);
	UConnectDataRequest request = new UConnectDataRequest("clearMsg", args.toArray(), sender);
	@SuppressWarnings("unused")
	UConnectRequestScheduler exec = new UConnectRequestScheduler("main", request, 5000, 5);
	//this.load(request);
	/*
	if(this.main.contains("messaging." + name)){
		this.main.set("messaging." + name, null);
	}
	this.save();
	*/
}
public void getMessages(UConnectProfile player, String page, CommandSender sender){
	this.cache = new File(plugin.getDataFolder().getAbsolutePath() + File.separator + "uConnect" + File.separator + "uConnectDataCache.txt");
	try {
		this.cache.createNewFile();
	} catch (IOException e) {
	}
	this.update(sender);
	String name = player.getName();
	List<String> args = new ArrayList<String>();
	args.add(name);
	args.add(page);
	UConnectDataRequest request = new UConnectDataRequest("getMsg", args.toArray(), sender);
	@SuppressWarnings("unused")
	UConnectRequestScheduler exec = new UConnectRequestScheduler("main", request, 5000, 5);
	//this.load(request);
	/*
	if(this.main.contains("messaging." + name)){
		return this.main.getStringList("messaging."+name);
	}
	else{
		List<String> result = new ArrayList<String>();
		//result.add("&aNo new messages");
		return result;
	}
	*/
	return;
}
public void update(CommandSender sender){
	UConnectDataRequest request = new UConnectDataRequest("reloadMain", null, sender);
	this.load(request); //Before using save in a method ALWAYS use load for security
}
public void loadProfiles(UConnectDataRequest request){
	try {
		this.cache = new File(plugin.getDataFolder().getAbsolutePath() + File.separator + "uConnect" + File.separator + "uConnectProfileDataCache.txt");
		this.cache.createNewFile();
	} catch (IOException e1) {
		return;
	}
	String uuid = UniqueString.generate();
	this.tasks.put(uuid, false);
	uConnectConnect.getFile("/profiles.yml", uuid, request);
	/*
	if(!profiles.contains("uconnect.create")){
		profiles.set("uconnect.create", true);
	}
    if(!profiles.contains("profiles.create")){
		profiles.set("profiles.create", true);
	}
	*/
	return;
}
public void saveProfiles(){
	try {
		this.cache = new File(plugin.getDataFolder().getAbsolutePath() + File.separator + "uConnect" + File.separator + "uConnectProfileDataCache.txt");
		this.cache.createNewFile();
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
	uConnectConnect.uploadYaml(this.profiles, "/profiles.yml", uuid);
	return;
}
public void updateProfiles(CommandSender sender){
	this.cache = new File(plugin.getDataFolder().getAbsolutePath() + File.separator + "uConnect" + File.separator + "uConnectProfileDataCache.txt");
	try {
		this.cache.createNewFile();
	} catch (IOException e) {
	}
	UConnectDataRequest request = new UConnectDataRequest("dummy", null, sender);
	loadProfiles(request);
	saveProfiles();
	return;
}
public void saveProfile(UConnectProfile profile, CommandSender sender){
	List<Object> args = new ArrayList<Object>();
	args.add(profile);
	UConnectDataRequest request = new UConnectDataRequest("saveProfile", args.toArray(), sender);
	@SuppressWarnings("unused")
	UConnectRequestScheduler exec = new UConnectRequestScheduler("profiles", request, 5000, 5);
	//loadProfiles(request);
	/*
	this.profiles.set("profiles." + name + ".online", profile.isOnline());
	saveProfiles();
	*/
	return;
}
public void loadProfile(String pname, UConnectDataRequest request, CommandSender sender){
	this.cache = new File(plugin.getDataFolder().getAbsolutePath() + File.separator + "uConnect" + File.separator + "uConnectProfileDataCache.txt");
	try {
		this.cache.createNewFile();
	} catch (IOException e) {
		e.printStackTrace();
	}
	loadProfiles(request);
	/*
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
		useful.plugin.colLogger.info("Contains profile");
	UConnectProfile profile = new UConnectProfile(pname);
	if(this.profiles.contains("profiles." + pname + ".online")){
		profile.setOnline(this.profiles.getBoolean("profiles."+pname+".online"));
	}
	return profile;	
	
	}
	else{
		useful.plugin.colLogger.info("Doesn't contain profile");
		return new UConnectProfile(pname);
	}
	*/
	return;
}
public void MessageCount(String pname, CommandSender sender){
	List<Object> args = new ArrayList<Object>();
	args.add(pname);
	UConnectDataRequest request = new UConnectDataRequest("alertMsg", args.toArray(), sender);
	@SuppressWarnings("unused")
	UConnectRequestScheduler exec = new UConnectRequestScheduler("main", request, 5000, 5);
	//this.load(request);
	/*
	if(this.main.contains("messaging."+pname)){
		return this.main.getStringList("messaging."+pname).size();
	}
	else{
		return 0;
	}
	*/
	return;
}

}
