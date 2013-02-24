package com.useful.useful.utils;

import org.bukkit.command.CommandSender;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class UConnectDataAvailableEvent extends Event{
	private UConnectDataRequest request = null;
	CommandSender requester = null;
	
	public UConnectDataAvailableEvent(UConnectDataRequest request, CommandSender requester){
		this.request = request;
		this.requester = requester;
	}

	@Override
	public HandlerList getHandlers() {
		return null;
	}
    public CommandSender getRequester(){
    	return this.requester;
    }
    public UConnectDataRequest getRequest(){
    	return this.request;
    }
}
