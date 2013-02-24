package com.useful.useful.utils;

import org.bukkit.command.CommandSender;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class UConnectDataAvailableEvent extends Event{
	private static final HandlerList handlers = new HandlerList();
	private UConnectDataRequest request = null;
	CommandSender requester = null;
	
	public UConnectDataAvailableEvent(UConnectDataRequest request, CommandSender requester){
		this.request = request;
		this.requester = requester;
	}

	public HandlerList getHandlers() {
        return handlers;
    }
 
    public static HandlerList getHandlerList() {
        return handlers;
    }
    public CommandSender getRequester(){
    	return this.requester;
    }
    public UConnectDataRequest getRequest(){
    	return this.request;
    }
}
