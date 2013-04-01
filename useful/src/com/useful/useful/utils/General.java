package com.useful.useful.utils;

public class General {
public static Object getTypeFromString(String raw){
	String type="unknown";
	float num = 0;
	try {
		num = Float.parseFloat(raw);
		type = "number";
	} catch (NumberFormatException e) {
		type = "unknown";
	}
	boolean bool = false;
	if(raw.equalsIgnoreCase("true")){
		bool = true;
		type = "boolean";
	}
	else if(raw.equalsIgnoreCase("false")){
		bool = false;
		type = "boolean";
	}
	if(type == "unknown"){
		//It is a string
	    return raw;
	}
	else if(type == "number"){
		//It is a number
		return num;
	}
	else if(type == "boolean"){
		//It is a boolean
		return bool;
	}
	else{
		return raw;
	}
}
}
