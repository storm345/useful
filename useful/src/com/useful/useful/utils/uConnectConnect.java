package com.useful.useful.utils;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.concurrent.CountDownLatch;

import javax.xml.ws.http.HTTPException;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpParams;
import org.bukkit.scheduler.BukkitTask;

import com.dropbox.client2.DropboxAPI;
import com.dropbox.client2.session.AccessTokenPair;
import com.dropbox.client2.session.AppKeyPair;
import com.dropbox.client2.session.WebAuthSession;
import com.dropbox.client2.session.Session.AccessType;
import com.useful.useful.useful;

public class uConnectConnect {
	private boolean loaded = false;
	private static SortedMap<String, String> oauth = new TreeMap<String, String>();
	public uConnectConnect(){
		this.loaded = true;
		this.oauth.put("oauth_consumer_key", "27y91t6ni72mhva");
		this.oauth.put("oauth_signature_method", "HMAZ-SHA1");
		this.oauth.put("oauth_version", "1.0");
		this.oauth.put("oauth_token", "l4yln3msdyua24o");
		//this.oauth.put("oauth_token_secret", "jf23d653v9cryms");
	}

	private static final String APP_KEY = "27y91t6ni72mhva";
    private static final String APP_SECRET = "nfni1r28rvapbhi";
    private static DropboxAPI<WebAuthSession> mDBApi = null;
    private static final AccessType ACCESS_TYPE = AccessType.APP_FOLDER; 
    	public static Boolean uploadFile(final File file, final String path, final String uuid){
    		useful.plugin.getServer().getScheduler().runTaskAsynchronously(useful.plugin, new Runnable(){

				@Override
				public void run() {
					try {
			        	AppKeyPair appKeys = new AppKeyPair(APP_KEY, APP_SECRET);
						WebAuthSession session = new WebAuthSession(appKeys, ACCESS_TYPE);
						AccessTokenPair tokens = new AccessTokenPair("l4yln3msdyua24o", "jf23d653v9cryms");
						session.setAccessTokenPair(tokens);
						FileInputStream in = new FileInputStream(file);
						mDBApi = new DropboxAPI<WebAuthSession>(session);
						mDBApi.putFileOverwrite(path, in, file.length(), null);
						/* setup again if i delete from dropbox
						WebAuthSession session = new WebAuthSession(appKeys, ACCESS_TYPE);
						WebAuthInfo authInfo = session.getAuthInfo();
						
						RequestTokenPair pair = authInfo.requestTokenPair;
						String url = authInfo.url;
			 
						Desktop.getDesktop().browse(new URL(url).toURI());
						JOptionPane.showMessageDialog(null, "Press ok to continue once you have authenticated.");
						session.retrieveWebAccessToken(pair);
						
						AccessTokenPair tokens = session.getAccessTokenPair();
						*/
						//key:  l4yln3msdyua24o secret:  jf23d653v9cryms   -   for dropbox
						//System.out.println("Use this token pair in future so you don't have to re-authenticate each time:");
						/*
						System.out.println("Key token: " + tokens.key);
						System.out.println("Secret token: " + tokens.secret);
						*/	
						//ByteArrayInputStream inputStream = new ByteArrayInputStream(fileContents.getBytes());
					} catch (Exception e) {
						useful.plugin.uconnect.tasks.put(uuid, true);
						e.printStackTrace();
					}
					useful.plugin.uconnect.tasks.put(uuid, true);
				}});
    		
        return true;
    	}
    	
    	public static File getFile(final String path, final File save, final String uuid){
    		useful.plugin.uconnect.tasks.put(uuid, false);
    		useful.plugin.getServer().getScheduler().runTaskAsynchronously(useful.plugin, new Runnable(){

				@Override
				public void run() {
					try {
    					AppKeyPair appKeys = new AppKeyPair(APP_KEY, APP_SECRET);
    	    			WebAuthSession session = new WebAuthSession(appKeys, ACCESS_TYPE);
    	    			AccessTokenPair tokens = new AccessTokenPair("l4yln3msdyua24o", "jf23d653v9cryms");
    	    			session.setAccessTokenPair(tokens);
    	    			mDBApi = new DropboxAPI<WebAuthSession>(session);
    	    			/* setup again if i delete from dropbox
    	    			WebAuthSession session = new WebAuthSession(appKeys, ACCESS_TYPE);
    	    			WebAuthInfo authInfo = session.getAuthInfo();
    	    			
    	    			RequestTokenPair pair = authInfo.requestTokenPair;
    	    			String url = authInfo.url;
    	     
    	    			Desktop.getDesktop().browse(new URL(url).toURI());
    	    			JOptionPane.showMessageDialog(null, "Press ok to continue once you have authenticated.");
    	    			session.retrieveWebAccessToken(pair);
    	    			
    	    			AccessTokenPair tokens = session.getAccessTokenPair();
    	    			*/
    	    			//key:  l4yln3msdyua24o secret:  jf23d653v9cryms   -   for storm345Dev dropbox
    	    			//System.out.println("Use this token pair in future so you don't have to re-authenticate each time:");
    	    			/*
    	    			System.out.println("Key token: " + tokens.key);
    	    			System.out.println("Secret token: " + tokens.secret);
    	    			*/
    	    			//ByteArrayInputStream inputStream = new ByteArrayInputStream(fileContents.getBytes());
    	    			FileOutputStream os;
    	    			useful.plugin.colLogger.info("Preparing to connect...");
						try {
							os = new FileOutputStream(save);
							useful.plugin.colLogger.info("Connecting...");
							mDBApi.getFile(path, null, os, null);
							os.close();
							useful.plugin.uconnect.tasks.put(uuid, true);
						} catch (Exception e) {
							useful.plugin.uconnect.tasks.put(uuid, true);
							e.printStackTrace();
						}
    		} catch (Exception e) {
    			e.printStackTrace();
    			useful.plugin.uconnect.tasks.put(uuid, true);
    		}
					useful.plugin.uconnect.tasks.put(uuid, true);
				}});
            while(!useful.plugin.uconnect.tasks.get(uuid)){
            	
            }
            return save;
        	}
    	public static void deleteFile(final String path, final String uuid){
    		useful.plugin.getServer().getScheduler().runTaskAsynchronously(useful.plugin, new Runnable(){

				@Override
				public void run() {
					try {
		    			AppKeyPair appKeys = new AppKeyPair(APP_KEY, APP_SECRET);
		    			WebAuthSession session = new WebAuthSession(appKeys, ACCESS_TYPE);
		    			AccessTokenPair tokens = new AccessTokenPair("l4yln3msdyua24o", "jf23d653v9cryms");
		    			session.setAccessTokenPair(tokens);
		    			mDBApi = new DropboxAPI<WebAuthSession>(session);
		    			mDBApi.delete(path);
		    		}
		    		catch (Exception e) {
		    			e.printStackTrace();
		    			useful.plugin.uconnect.tasks.put(uuid, true);
		    			return;
		    		}
		    		useful.plugin.uconnect.tasks.put(uuid, true);
				}});
    		
    		return;
    	}
    	public static DropboxAPI<WebAuthSession> getApi(){
    		try {
    			AppKeyPair appKeys = new AppKeyPair(APP_KEY, APP_SECRET);
    			WebAuthSession session = new WebAuthSession(appKeys, ACCESS_TYPE);
    			AccessTokenPair tokens = new AccessTokenPair("l4yln3msdyua24o", "jf23d653v9cryms");
    			session.setAccessTokenPair(tokens);
    			mDBApi = new DropboxAPI<WebAuthSession>(session);
                return mDBApi;
    		}
    		catch (Exception e) {
    			e.printStackTrace();
    			return null;
    		}
    	}
    	
    
}
