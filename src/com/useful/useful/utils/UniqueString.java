package com.useful.useful.utils;


public class UniqueString {
public static String generate(){
	String string = "";
	long time = System.currentTimeMillis();
	int rand = 1 + (int)(Math.random() * ((10 - 1) + 1));
	if(rand > 5){
		string = "FDF8D78";
				
	}
	else if(rand > 5){
		string = "ER876ER8";
	}
	else{
		string = "FD9F87D9";
	}
	int rand2 = 1 + (int)(Math.random() * ((999999 - 1) + 1));
	string = string + rand + time + rand2;
	return string;
}
public static String key(){
	return "27y91t6ni72mhvanfni1r28rvapbhil4yln3msdyua24ojf23d653v9cryms";
}
}
