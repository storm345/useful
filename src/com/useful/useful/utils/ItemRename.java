package com.useful.useful.utils;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.useful.useful.useful;

public class ItemRename {
	public ItemRename(useful instance){
		//Made a new itemrenamer
	}
	public ItemStack rename(ItemStack item, String name){
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(name);
		item.setItemMeta(meta);
		return item;
	}
	public boolean hasCustomName(ItemStack item){
		if(item.getItemMeta().hasDisplayName()){
			return true;
		}
		return false;
	}
}
