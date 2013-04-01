package com.useful.useful.utils;

import java.util.ArrayList;

import com.useful.useful.useful;



public class JailInfo {
	public static ArrayList<String> info = new ArrayList<String>();
	
public static ArrayList<String> getPlayerJailInfo(String pname){
	info = useful.jailed.get(pname);
	return info;
}
	
	
}
