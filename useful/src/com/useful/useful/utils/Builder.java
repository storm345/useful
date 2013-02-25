package com.useful.useful.utils;

import java.util.HashMap;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;

import com.useful.useful.useful;

public class Builder {
	public static HashMap<String, Boolean> tests = new HashMap<String, Boolean>();
	public static Boolean canBuild(Player player, Location loc){
		Block blockToPlaceIt = loc.getBlock();
		Block old = blockToPlaceIt;
		BlockState orig = old.getState();
		Block newBlock = blockToPlaceIt;
		Material material = Material.AIR;
		tests.put(player.getName(), false);
		//newBlock.setTypeId(5);
		BlockState state = newBlock.getState();
		Block pacedOn = blockToPlaceIt;
		Event check = new CheckPermBlockEvent(newBlock, material, state, pacedOn, new ItemStack(5), player, false, orig);
		useful.plugin.getServer().getPluginManager().callEvent(check);
		if(tests.containsKey(player.getName())){
			Boolean result = tests.get(player.getName());
			tests.remove(player.getName());
			return result;
		}
		return false;
	}
public static void build(Player player, Block blockToPlaceIt, Material mat){
	Block old = blockToPlaceIt;
	BlockState orig = old.getState();
	Block newBlock = blockToPlaceIt;
	Material material = mat;
	//newBlock.setTypeId(5);
	BlockState state = newBlock.getState();
	Block pacedOn = blockToPlaceIt;
	Event check = new PermSafeBlockPlaceEvent(newBlock, material, state, pacedOn, new ItemStack(5), player, false, orig);
	useful.plugin.getServer().getPluginManager().callEvent(check);
	return;
}
public static void buildById(Player player, Block blockToPlaceIt, String id){
	String[] idParts = id.split(":");
	int length = idParts.length;
	int tid = Integer.parseInt(idParts[0]);
	short data = 0;
	if(length > 1){
	data = Short.parseShort(idParts[1]);
	}
	ItemStack item = new ItemStack(tid);
	item.setDurability(data);
	Byte tdata = item.getData().getData();
	if(data < 1){
		tdata = null;
	}
	Block old = blockToPlaceIt;
	BlockState orig = old.getState();
	Block newBlock = blockToPlaceIt;
	//newBlock.setTypeId(5);
	BlockState state = newBlock.getState();
	Block pacedOn = blockToPlaceIt;
	Event check = new PermSafeBlockPlaceEvent(newBlock, tid, tdata, state, pacedOn, new ItemStack(5), player, false, orig);
	useful.plugin.getServer().getPluginManager().callEvent(check);
	return;
}
}
