package com.useful.useful.utils;



import java.util.ArrayList;
import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import com.useful.useful.useful;

public class ColoredLogger {
	static useful plugin = useful.plugin;
	public ColoredLogger(useful instance){
		plugin = useful.plugin;
	}
	private ConsoleCommandSender console = plugin.getServer().getConsoleSender();
public void info(String msg){
	console.sendMessage(ChatColor.GOLD + "[Useful] " + ChatColor.YELLOW + "" + msg);
	return;
}
public void log(Level level, String msg){
	Bukkit.getLogger().log(level, msg);
	ArrayList<String> players = plugin.commandViewers.values;
	Object[] loggers = players.toArray();
	for (int i = 0; i < loggers.length; i++) {
         Player p = plugin.getServer().getPlayer((String) loggers[i]);
    if(p != null){     
    	p.sendMessage(ChatColor.DARK_GRAY + "ERROR: " + level.getName() + "  "+msg);
    }
	}
	return;
}
public void log(Level level, String msg, Throwable thrown){
	Bukkit.getLogger().log(level, msg, thrown);
	String tMsg = thrown.getMessage();
	ArrayList<String> players = plugin.commandViewers.values;
	Object[] loggers = players.toArray();
	for (int i = 0; i < loggers.length; i++) {
         Player p = plugin.getServer().getPlayer((String) loggers[i]);
    if(p != null){     
    	p.sendMessage(ChatColor.DARK_GRAY + "ERROR: " + level.getName() + "  "+msg+"  Error msg: " + tMsg);
    }
	}
	return;
}
public void warning(String msg){
	console.sendMessage(ChatColor.GOLD + "[Useful] " + ChatColor.RED + "" + msg);
	ArrayList<String> players = plugin.commandViewers.values;
	Object[] loggers = players.toArray();
	for (int i = 0; i < loggers.length; i++) {
         Player p = plugin.getServer().getPlayer((String) loggers[i]);
    if(p != null){     
    	p.sendMessage(ChatColor.DARK_GRAY+"WARNING: " + msg);
    }
	}
	return;
}
}
