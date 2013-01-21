package com.useful.useful.utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.plugin.Plugin;

import com.useful.useful.useful;

public class Performance {
		static useful plugin;
		public static int gc;
			
			public ListStore heros;
			
			public Performance(useful instance){
				plugin = useful.plugin;
			}
			

			public static void performanceMode(Boolean enable){
			if(enable == false){
				//disable everything setup below
				Bukkit.getScheduler().cancelTask(gc);
			return;
			}
			gc = Bukkit.getScheduler().scheduleAsyncRepeatingTask(useful.plugin, new Runnable() {

	            @Override
	            public void run() {
	            	if(useful.uhost_settings.get("performance")){
	            	System.gc();
	            	}
	            }

				}, 2000, 2000);
			
			
			}
}
