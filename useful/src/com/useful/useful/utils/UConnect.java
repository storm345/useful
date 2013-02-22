package com.useful.useful.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

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
}
