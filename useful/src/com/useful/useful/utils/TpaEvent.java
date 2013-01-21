package com.useful.useful.utils;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class TpaEvent extends Event {
    private static final HandlerList handlers = new HandlerList();
    private Player sender;
    private Player target;
    private Location loc;
    private boolean tpa;
    public TpaEvent(Player esender, Location eloc, Player etarget, boolean etpa) {
        sender = esender;
        loc = eloc;
        target = etarget;
        tpa = etpa;
    }
 
    public Player getSender() {
        return sender;
    }
    
    public boolean getTpa() {
        return tpa;
    }
    
    public Player getTarget() {
    	return target;
    }
    
    public Location getLocation() {
    	return loc;
    }
 
    public HandlerList getHandlers() {
        return handlers;
    }
 
    public static HandlerList getHandlerList() {
        return handlers;
    }
}
