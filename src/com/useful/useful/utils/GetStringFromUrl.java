package com.useful.useful.utils;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class GetStringFromUrl {
	public static String get(String webAdress){
		String result = null;
		URL url = null;
			InputStream in = null;
		try {
			url = new URL(webAdress);
		} catch (MalformedURLException e1) {
		}
			try {
			in = new BufferedInputStream(url.openStream());
		} catch (Exception e1) {
			
		}
			try{
				BufferedReader reader = new BufferedReader(new InputStreamReader(in));   				
				String line;
				while((line = reader.readLine()) != null){
					result = line;
				}
			} catch (Exception e){
				
			}
	return result;
	}
	

}
