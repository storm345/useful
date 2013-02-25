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
	string = string + rand + time;
	return string;
}
}
