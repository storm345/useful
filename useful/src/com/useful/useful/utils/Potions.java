package com.useful.useful.utils;

import org.bukkit.potion.PotionType;

import com.useful.useful.useful;

public class Potions {
public Potions(useful instance){
}
public PotionType potionTypeFromString(String string){
	if(string.equalsIgnoreCase("fire_resistance")){
		return PotionType.FIRE_RESISTANCE;
	}
	else if(string.equalsIgnoreCase("instant_damage")){
		return PotionType.INSTANT_DAMAGE;
	}
	else if(string.equalsIgnoreCase("instant_heal")){
		return PotionType.INSTANT_HEAL;
	}
	else if(string.equalsIgnoreCase("invisibility")){
		return PotionType.INVISIBILITY;
	}
	else if(string.equalsIgnoreCase("night_vision")){
		return PotionType.NIGHT_VISION;
	}
	else if(string.equalsIgnoreCase("poison")){
		return PotionType.POISON;
	}
	else if(string.equalsIgnoreCase("regen")){
		return PotionType.REGEN;
	}
	else if(string.equalsIgnoreCase("slowness")){
		return PotionType.SLOWNESS;
	}
	else if(string.equalsIgnoreCase("speed")){
		return PotionType.SPEED;
	}
	else if(string.equalsIgnoreCase("strength")){
		return PotionType.STRENGTH;
	}
	else if(string.equalsIgnoreCase("water")){
		return PotionType.WATER;
	}
	else if(string.equalsIgnoreCase("weakness")){
		return PotionType.WEAKNESS;
	}
	else{
	return null;
	}
}
}
