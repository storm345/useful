package com.useful.useful.utils;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;

import com.useful.useful.useful;

public class Entities {
private static useful plugin;
	
	public ListStore heros;
	
	public Entities(useful plugin){
		this.plugin = plugin;
	}
	
	
	public static EntityType getEntityTypeByName(String name, CommandSender sender){
		if (name.equalsIgnoreCase("cow")){
			if (sender.hasPermission("useful.mob.cow")){
			return EntityType.COW;
			}
			else {
				sender.sendMessage(ChatColor.RED + "You are not allowed to spawn this!");
			}
		}
		else if (name.equalsIgnoreCase("Chicken")){
			if (sender.hasPermission("useful.mob.chicken")){
				return EntityType.CHICKEN;
				}
				else {
					sender.sendMessage(ChatColor.RED + "You are not allowed to spawn this!");
				}
		}
		else if (name.equalsIgnoreCase("Firework")){
			if (sender.hasPermission("useful.mob.firework")){
				return EntityType.FIREWORK;
				}
				else {
					sender.sendMessage(ChatColor.RED + "You are not allowed to spawn this!");
				}
		}
		else if (name.equalsIgnoreCase("blaze")){
			if (sender.hasPermission("useful.mob.blaze")){
				return EntityType.BLAZE;
				}
				else {
					sender.sendMessage(ChatColor.RED + "You are not allowed to spawn this!");
				}
		}
		else if (name.equalsIgnoreCase("cave-spider")){
			if (sender.hasPermission("useful.mob.cavespider")){
				return EntityType.CAVE_SPIDER;
				}
				else {
					sender.sendMessage(ChatColor.RED + "You are not allowed to spawn this!");
				}
		}
		else if (name.equalsIgnoreCase("creeper")){
			if (sender.hasPermission("useful.mob.creeper")){
				return EntityType.CREEPER;
				}
				else {
					sender.sendMessage(ChatColor.RED + "You are not allowed to spawn this!");
				}
		}
		else if (name.equalsIgnoreCase("dragon")){
			if (sender.hasPermission("useful.mob.enderdragon")){
				return EntityType.ENDER_DRAGON;
				}
				else {
					sender.sendMessage(ChatColor.RED + "You are not allowed to spawn this!");
				}
		}
		else if (name.equalsIgnoreCase("enderdragon")){
			if (sender.hasPermission("useful.mob.enderdragon")){
				return EntityType.ENDER_DRAGON;
				}
				else {
					sender.sendMessage(ChatColor.RED + "You are not allowed to spawn this!");
				}
		}
		else if (name.equalsIgnoreCase("enderman")){
			if (sender.hasPermission("useful.mob.enderman")){
				return EntityType.ENDERMAN;
				}
				else {
					sender.sendMessage(ChatColor.RED + "You are not allowed to spawn this!");
				}
		}
		else if (name.equalsIgnoreCase("xp")){
			if (sender.hasPermission("useful.mob.xp")){
				return EntityType.EXPERIENCE_ORB;
				}
				else {
					sender.sendMessage(ChatColor.RED + "You are not allowed to spawn this!");
				}
		}
		else if (name.equalsIgnoreCase("ghast")){
			if (sender.hasPermission("useful.mob.ghast")){
				return EntityType.GHAST;
				}
				else {
					sender.sendMessage(ChatColor.RED + "You are not allowed to spawn this!");
				}
		}
		else if (name.equalsIgnoreCase("giant")){
			if (sender.hasPermission("useful.mob.giant")){
				return EntityType.GIANT;
				}
				else {
					sender.sendMessage(ChatColor.RED + "You are not allowed to spawn this!");
				}
		}
		else if (name.equalsIgnoreCase("irongolem")){
			if (sender.hasPermission("useful.mob.irongolem")){
				return EntityType.IRON_GOLEM;
				}
				else {
					sender.sendMessage(ChatColor.RED + "You are not allowed to spawn this!");
				}
		}
		else if (name.equalsIgnoreCase("magmacube")){
			if (sender.hasPermission("useful.mob.magmacube")){
				return EntityType.MAGMA_CUBE;
				}
				else {
					sender.sendMessage(ChatColor.RED + "You are not allowed to spawn this!");
				}
		}
		else if (name.equalsIgnoreCase("mushroomcow")){
			if (sender.hasPermission("useful.mob.mushroomcow")){
				return EntityType.MUSHROOM_COW;
				}
				else {
					sender.sendMessage(ChatColor.RED + "You are not allowed to spawn this!");
				}
		}
		else if (name.equalsIgnoreCase("ocelot")){
			if (sender.hasPermission("useful.mob.ocelot")){
				return EntityType.OCELOT;
				}
				else {
					sender.sendMessage(ChatColor.RED + "You are not allowed to spawn this!");
				}
		}
		else if (name.equalsIgnoreCase("pig")){
			if (sender.hasPermission("useful.mob.pig")){
				return EntityType.PIG;
				}
				else {
					sender.sendMessage(ChatColor.RED + "You are not allowed to spawn this!");
				}
		}
		else if (name.equalsIgnoreCase("pigzombie")){
			if (sender.hasPermission("useful.mob.pigzombie")){
				return EntityType.PIG_ZOMBIE;
				}
				else {
					sender.sendMessage(ChatColor.RED + "You are not allowed to spawn this!");
				}
		}
		else if (name.equalsIgnoreCase("silverfish")){
			if (sender.hasPermission("useful.mob.silverfish")){
				return EntityType.SILVERFISH;
				}
				else {
					sender.sendMessage(ChatColor.RED + "You are not allowed to spawn this!");
				}
		}
		else if (name.equalsIgnoreCase("sheep")){
			if (sender.hasPermission("useful.mob.sheep")){
				return EntityType.SHEEP;
				}
				else {
					sender.sendMessage(ChatColor.RED + "You are not allowed to spawn this!");
				}
		}
		else if (name.equalsIgnoreCase("skeleton")){
			if (sender.hasPermission("useful.mob.skelton")){
				return EntityType.SKELETON;
				}
				else {
					sender.sendMessage(ChatColor.RED + "You are not allowed to spawn this!");
				}
		}
		else if (name.equalsIgnoreCase("slime")){
			if (sender.hasPermission("useful.mob.slime")){
				return EntityType.SLIME;
				}
				else {
					sender.sendMessage(ChatColor.RED + "You are not allowed to spawn this!");
				}
		}
		else if (name.equalsIgnoreCase("snowman")){
			if (sender.hasPermission("useful.mob.snowman")){
				return EntityType.SNOWMAN;
				}
				else {
					sender.sendMessage(ChatColor.RED + "You are not allowed to spawn this!");
				}
		}
		else if (name.equalsIgnoreCase("spider")){
			if (sender.hasPermission("useful.mob.spider")){
				return EntityType.SPIDER;
				}
				else {
					sender.sendMessage(ChatColor.RED + "You are not allowed to spawn this!");
				}
		}
		else if (name.equalsIgnoreCase("squid")){
			if (sender.hasPermission("useful.mob.squid")){
				return EntityType.SQUID;
				}
				else {
					sender.sendMessage(ChatColor.RED + "You are not allowed to spawn this!");
				}
		}
		else if (name.equalsIgnoreCase("villager")){
			if (sender.hasPermission("useful.mob.villager")){
				return EntityType.VILLAGER;
				}
				else {
					sender.sendMessage(ChatColor.RED + "You are not allowed to spawn this!");
				}
		}
		else if (name.equalsIgnoreCase("wolf")){
			if (sender.hasPermission("useful.mob.wolf")){
				return EntityType.WOLF;
				}
				else {
					sender.sendMessage(ChatColor.RED + "You are not allowed to spawn this!");
				}
		}
		else if (name.equalsIgnoreCase("zombie")){
			if (sender.hasPermission("useful.mob.zombie")){
				return EntityType.ZOMBIE;
				}
				else {
					sender.sendMessage(ChatColor.RED + "You are not allowed to spawn this!");
				}
		}
		else if (name.equalsIgnoreCase("bat")){
			if (sender.hasPermission("useful.mob.bat")){
				return EntityType.BAT;
				}
				else {
					sender.sendMessage(ChatColor.RED + "You are not allowed to spawn this!");
				}
		}
		else if (name.equalsIgnoreCase("witch")){
			if (sender.hasPermission("useful.mob.witch")){
				return EntityType.WITCH;
				}
				else {
					sender.sendMessage(ChatColor.RED + "You are not allowed to spawn this!");
				}
		}
		else if (name.equalsIgnoreCase("wither")){
			if (sender.hasPermission("useful.mob.wither")){
				return EntityType.WITHER;
				}
				else {
					sender.sendMessage(ChatColor.RED + "You are not allowed to spawn this!");
				}
		}
		else if (name.equalsIgnoreCase("wither_skull")){
			if (sender.hasPermission("useful.mob.witherskull")){
				return EntityType.WITHER_SKULL;
				}
				else {
					sender.sendMessage(ChatColor.RED + "You are not allowed to spawn this!");
				}
		}
		else {
			sender.sendMessage(ChatColor.RED + "Mob type not recognised. Do /mobtypes for a list of them.");
			return null;
		}
		return null;
	}
}
