package com.useful.useful.utils;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.ShapelessRecipe;

import com.useful.useful.useful;

public class CustomRecipes {
    ShapelessRecipe dblSlab = null;
    ShapelessRecipe dlbSlab2Single = null;
    ShapelessRecipe waterSource = null;
    ShapelessRecipe waterBucket = null;
    ShapelessRecipe lavaSource = null;
    ShapelessRecipe lavaBucket = null;
    ShapelessRecipe snowBlock = null;
    ShapelessRecipe ice = null;
    ShapelessRecipe fire = null;
    ShapelessRecipe snowBall = null;
    ShapedRecipe mobSpawner = null;
    ShapedRecipe cmdBlock = null;
public CustomRecipes(){
	dblSlab = new ShapelessRecipe(new ItemStack(Material.DOUBLE_STEP));
	dblSlab.addIngredient(2, Material.STEP);
	ItemStack singleSlabs = new ItemStack(Material.STEP);
	singleSlabs.setAmount(2);
	dlbSlab2Single = new ShapelessRecipe(singleSlabs);
	dlbSlab2Single.addIngredient(1, Material.DOUBLE_STEP);	
	ItemStack water = new ItemStack(Material.WATER);
	waterSource = new ShapelessRecipe(water);
	waterSource.addIngredient(Material.WATER_BUCKET);
	ItemStack waterBucketItem = new ItemStack(Material.WATER_BUCKET);
	waterBucket = new ShapelessRecipe(waterBucketItem);
	waterBucket.addIngredient(Material.WATER);
	waterBucket.addIngredient(Material.BUCKET);
	ItemStack lava = new ItemStack(Material.LAVA);
	lavaSource = new ShapelessRecipe(lava);
	lavaSource.addIngredient(Material.LAVA_BUCKET);
	ItemStack lavaBucketItem = new ItemStack(Material.LAVA_BUCKET);
	lavaBucket = new ShapelessRecipe(lavaBucketItem);
	lavaBucket.addIngredient(Material.LAVA);
	lavaBucket.addIngredient(Material.BUCKET);
	mobSpawner = new ShapedRecipe(new ItemStack(Material.MOB_SPAWNER));
	mobSpawner.shape("---", "-0-", "-2-");
	mobSpawner.setIngredient('-', Material.IRON_FENCE);
	mobSpawner.setIngredient('0', Material.DISPENSER);
	mobSpawner.setIngredient('2', Material.ROTTEN_FLESH);
	cmdBlock = new ShapedRecipe(new ItemStack(Material.COMMAND));
	cmdBlock.shape("-1-", "101", "-1-");
	cmdBlock.setIngredient('-', Material.OBSIDIAN);
	cmdBlock.setIngredient('1', Material.GOLD_BLOCK);
	cmdBlock.setIngredient('0', Material.REDSTONE);
	snowBlock = new ShapelessRecipe(new ItemStack(Material.SNOW_BLOCK));
	snowBlock.addIngredient(9, Material.SNOW_BALL);
	ice = new ShapelessRecipe(new ItemStack(Material.ICE));
	ice.addIngredient(Material.WATER);
	ice.addIngredient(Material.SNOW_BALL);
	fire = new ShapelessRecipe(new ItemStack(Material.FIRE));
	fire.addIngredient(Material.FIREBALL);
	ItemStack snowballs = new ItemStack(Material.SNOW_BALL);
	snowballs.setAmount(9);
	snowBall = new ShapelessRecipe(snowballs);
	snowBall.addIngredient(Material.SNOW_BLOCK);
}
public void Register(){
	//TODO
	useful.plugin.getServer().addRecipe(dblSlab);
	useful.plugin.getServer().addRecipe(dlbSlab2Single);
	useful.plugin.getServer().addRecipe(waterSource);
	useful.plugin.getServer().addRecipe(waterBucket);
	useful.plugin.getServer().addRecipe(lavaSource);
	useful.plugin.getServer().addRecipe(lavaBucket);
	useful.plugin.getServer().addRecipe(mobSpawner);
	useful.plugin.getServer().addRecipe(cmdBlock);
	useful.plugin.getServer().addRecipe(snowBlock);
	useful.plugin.getServer().addRecipe(ice);
	useful.plugin.getServer().addRecipe(fire);
	useful.plugin.getServer().addRecipe(snowBall);
	return;
}
}
