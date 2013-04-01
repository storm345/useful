package com.useful.useful.utils;

import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitTask;

import com.useful.useful.useful;

public class Performance {
		static useful plugin;
		public static BukkitTask gc;
			
			public ListStore heros;
			
			public Performance(useful instance){
				plugin = useful.plugin;
			}
			

			public static void performanceMode(Boolean enable){
			if(enable == false){
				//disable everything setup below
				gc = Bukkit.getScheduler().runTaskTimerAsynchronously(useful.plugin, new Runnable() {

		            @Override
		            public void run() {
		            	//nothing
		            }

					}, 2000, 2000);
				gc.cancel();
				
			return;
			}
			gc = Bukkit.getScheduler().runTaskTimerAsynchronously(useful.plugin, new Runnable() {

	            @Override
	            public void run() {
	            	if(useful.uhost_settings.get("performance")){
	            	System.gc();
	            	}
	            }

				}, 2000, 2000);
			
			
			}
}
