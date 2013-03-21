package com.useful.useful.utils;


import com.useful.useful.useful;

public class Colors {
	private String success = "";
	private String error = "";
	private String info = "";
	private String title = "";
	private String tp = "";
	public Colors(String success, String error, String info, String title, String tp){
		this.success = useful.colorise(success);
		this.error = useful.colorise(error);
		this.info = useful.colorise(info);
		this.title = useful.colorise(title);
		this.tp = useful.colorise(tp);
	}
	
public String getSuccess(){
	return this.success;
}
public String getError(){
	return this.error;
}
public String getInfo(){
	return this.info;
}
public String getTitle(){
	return this.title;
}
public String getTp(){
	return this.tp;
}
}
