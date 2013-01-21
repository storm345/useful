package com.useful.useful.utils;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;

public class PermSafeBlockPlaceEvent extends BlockPlaceEvent {
	private BlockState orig = null;
    private Location loc = null;
    private Byte bData = null;
    private Material mat = null;
    private int id = 0;
    private Byte rawData = null;
	public PermSafeBlockPlaceEvent(Block placedBlock, Material mat, 
			BlockState replacedBlockState, Block placedAgainst,
			ItemStack itemInHand, Player thePlayer, boolean canBuild, BlockState originalBlockData) {
		super(placedBlock, replacedBlockState, placedAgainst, itemInHand, thePlayer,
				canBuild);
		this.mat = mat;
		this.id = mat.getId();
		this.rawData = null;
		this.orig = originalBlockData;
		this.loc = placedBlock.getLocation();
		this.bData = placedBlock.getData();
	}
	public PermSafeBlockPlaceEvent(Block placedBlock, int id, Byte rawData,  
			BlockState replacedBlockState, Block placedAgainst,
			ItemStack itemInHand, Player thePlayer, boolean canBuild, BlockState originalBlockData) {
		super(placedBlock, replacedBlockState, placedAgainst, itemInHand, thePlayer,
				canBuild);
		ItemStack item = new ItemStack(id);
		if(rawData != null){
		item.setDurability(Short.parseShort("" + rawData));
		}
		Material mat = item.getType();
		this.mat = mat;
		this.id = id;
		this.rawData = rawData;
		if(rawData == null){
			this.rawData = null;
		}
		this.orig = originalBlockData;
		this.loc = placedBlock.getLocation();
		this.bData = placedBlock.getData();
	}
public void undo(){
	if(this.orig instanceof Sign){
		Sign sign = (Sign) this.orig;
		String zero = sign.getLine(0);
		String one = sign.getLine(1);
		String two = sign.getLine(2);
		String three = sign.getLine(3);
		sign.update(true);
		BlockState bl = this.orig.getBlock().getState();
		if(bl instanceof Sign){
			((Sign) bl).setLine(0, zero);
			((Sign) bl).setLine(1, one);
			((Sign) bl).setLine(2, two);
			((Sign) bl).setLine(3, three);
			bl.update(true);
		}
	}
	else{
	this.orig.update(true);
	this.orig.getBlock().setData(this.orig.getData().getData());
	}
	return;
}
public void place(){
	loc.getBlock().setType(mat);
	loc.getBlock().setData(this.bData);
	loc.getBlock().setTypeId(this.id);
	BlockState now = loc.getBlock().getState();
	if(rawData != null){
	now.setRawData(rawData);
	now.update(true);
	}
	return;
}
	
}
