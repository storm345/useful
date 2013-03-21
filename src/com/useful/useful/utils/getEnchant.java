package com.useful.useful.utils;

import org.bukkit.enchantments.Enchantment;

public class getEnchant {
public static Enchantment getEnchantFromString(String string){
	if(string.equalsIgnoreCase("arrow_damage")){
		return Enchantment.ARROW_DAMAGE;
	}
	else if(string.equalsIgnoreCase("arrow_fire")){
		return Enchantment.ARROW_FIRE;
	}
	else if(string.equalsIgnoreCase("arrow_infinite")){
		return Enchantment.ARROW_INFINITE;
	}
	else if(string.equalsIgnoreCase("arrow_knockback")){
		return Enchantment.ARROW_KNOCKBACK;
	}
	else if(string.equalsIgnoreCase("damage_all")){
		return Enchantment.DAMAGE_ALL;
	}
	else if(string.equalsIgnoreCase("damage_spiders")){
		return Enchantment.DAMAGE_ARTHROPODS;
	}
	else if(string.equalsIgnoreCase("damage_zombies")){
		return Enchantment.DAMAGE_UNDEAD;
	}
	else if(string.equalsIgnoreCase("haste")){
		return Enchantment.DIG_SPEED;
	}
	else if(string.equalsIgnoreCase("durability")){
		return Enchantment.DURABILITY;
	}
	else if(string.equalsIgnoreCase("fire_aspect")){
		return Enchantment.FIRE_ASPECT;
	}
	else if(string.equalsIgnoreCase("knockback")){
		return Enchantment.KNOCKBACK;
	}
	else if(string.equalsIgnoreCase("loot_bonus_blocks")){
		return Enchantment.LOOT_BONUS_BLOCKS;
	}
	else if(string.equalsIgnoreCase("loot_bonus_mobs")){
		return Enchantment.LOOT_BONUS_MOBS;
	}
	else if(string.equalsIgnoreCase("oxygen")){
		return Enchantment.OXYGEN;
	}
	else if(string.equalsIgnoreCase("environmental_protection")){
		return Enchantment.PROTECTION_ENVIRONMENTAL;
	}
	else if(string.equalsIgnoreCase("explosion_protection")){
		return Enchantment.PROTECTION_EXPLOSIONS;
	}
	else if(string.equalsIgnoreCase("fall_protection")){
		return Enchantment.PROTECTION_FALL;
	}
	else if(string.equalsIgnoreCase("fire_protection")){
		return Enchantment.PROTECTION_FIRE;
	}
	else if(string.equalsIgnoreCase("arrow_protection")){
		return Enchantment.PROTECTION_PROJECTILE;
	}
	else if(string.equalsIgnoreCase("silk_touch")){
		return Enchantment.SILK_TOUCH;
	}
	else if(string.equalsIgnoreCase("thorns")){
		return Enchantment.THORNS;
	}
	else if(string.equalsIgnoreCase("water_worker")){
		return Enchantment.WATER_WORKER;
	}
	else{
		return null;
	}
}
}
