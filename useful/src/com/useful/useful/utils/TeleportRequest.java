package com.useful.useful.utils;

import java.util.HashMap;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import com.useful.useful.useful;

public class TeleportRequest {
	public static HashMap<Player, TpaReq> requests = new HashMap<Player, TpaReq>();
	private static Plugin plugin = useful.plugin;
public static void addTeleportRequest(Player toRequest, TpaReq req) {
	if(requests.containsKey(toRequest)){
		requests.remove(toRequest);
	}
	Player target = plugin.getServer().getPlayer(req.getAccepter());
	requests.put(target, req);
	if(target == null){
		//should NEVER happen
		return;
	}
	TpaEvent event = new TpaEvent(toRequest, target.getLocation(), target, req.getTpa());
	// Call the event
	Bukkit.getServer().getPluginManager().callEvent(event);
	return;
}
public static TpaReq getTeleportRequest(Player player){
	if(requests.containsKey(player) == false){
		return null;
	}
	return (TpaReq) requests.get(player);
}
}
