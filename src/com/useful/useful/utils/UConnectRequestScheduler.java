package com.useful.useful.utils;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import com.useful.useful.useful;

public class UConnectRequestScheduler {
	private String yaml = "";
	private UConnectDataRequest request = null;
	private long delay = 0;
	private int tries = 0;
	public UConnectRequestScheduler(String yaml, UConnectDataRequest request, long delay, int tries){
		this.yaml = yaml;
		this.request = request;
		this.delay = delay;
		this.tries = tries;
		this.start();
	}
	public void start(){
		final String tyaml = this.yaml;
		final UConnectDataRequest trequest = this.request;
         final long tdelay = this.delay;
				final int tttries = this.tries;
				useful.plugin.getServer().getScheduler().runTaskAsynchronously(useful.plugin, new Runnable(){
					int tries = tttries;
					@Override
					public void run() {
						boolean run = true;
						for(@SuppressWarnings("unused")
						int i=0;tries>0&&run;tries--){
							if(trequest.getSender() instanceof Player){
								Player p = (Player) trequest.getSender();
								if(!p.isOnline()){
									run = false;
									//tries = 0;
								}
							}
							if(useful.plugin.uconnect.tasks.size() > 5){
								trequest.getSender().sendMessage(ChatColor.GRAY + "UConnect busy. Retrying in "+tdelay/1000+" seconds. You have "+tries + " tries left!");
									try {
										Thread.sleep(tdelay);
									} catch (InterruptedException e) {
										run = false;
										//tries = 0;
									}
									
							}
							else{
								//TODO
								if(tyaml.equalsIgnoreCase("main")){
									useful.plugin.uconnect.load(trequest);
									run = false;
									//tries = 0;
									}
									else if(tyaml.equalsIgnoreCase("profiles")){
										useful.plugin.uconnect.loadProfiles(trequest, trequest.getAuth());
										run = false;
										//tries = 0;
									}
									else if(tyaml.equalsIgnoreCase("profile")){
										useful.plugin.uconnect.loadProfile(trequest.getSender().getName(), trequest, trequest.getSender());
										run = false;
										//tries = 0;
									}
									else{
										trequest.getSender().sendMessage(useful.plugin.colors.getError() + "Invalid request-Contact author of command");
										run = false;
										//tries = 0;
									}
							}
							if(tries < 1){
								trequest.getSender().sendMessage(ChatColor.GRAY + "Connection timed out - no more tries left. Please try again in a minute");
								return;
							}
		                //run = false;
						}
						
					}});
		return;
	}
public long getRemainingTries(){
	return this.tries;
}
public long getDelay(){
	return this.delay;
}
public String getYaml(){
	return this.yaml;
}
public UConnectDataRequest getRequest(){
	return this.request;
}
public void stop(){
	this.tries = 0;
	return;
}
}
