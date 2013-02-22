package com.useful.useful.utils;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import com.dropbox.client2.DropboxAPI;
import com.dropbox.client2.DropboxAPI.Entry;
import com.dropbox.client2.session.AccessTokenPair;
import com.dropbox.client2.session.AppKeyPair;
import com.dropbox.client2.session.Session.AccessType;
import com.dropbox.client2.session.WebAuthSession;

public class uConnectConnect {
	private boolean loaded = false;
	
	public uConnectConnect(){
		this.loaded = true;
	}

	private static final String APP_KEY = "27y91t6ni72mhva";
    private static final String APP_SECRET = "nfni1r28rvapbhi";
    private static final AccessType ACCESS_TYPE = AccessType.APP_FOLDER;
    private static DropboxAPI<WebAuthSession> mDBApi;
 
    	public static Boolean uploadFile(File file, String path){
    	
        try {
			AppKeyPair appKeys = new AppKeyPair(APP_KEY, APP_SECRET);
			WebAuthSession session = new WebAuthSession(appKeys, ACCESS_TYPE);
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
			AccessTokenPair tokens = new AccessTokenPair("l4yln3msdyua24o", "jf23d653v9cryms");
			session.setAccessTokenPair(tokens);
			mDBApi = new DropboxAPI<WebAuthSession>(session);
			DataInputStream input = new DataInputStream(new FileInputStream(file));
			//ByteArrayInputStream inputStream = new ByteArrayInputStream(fileContents.getBytes());
			@SuppressWarnings("unused")
			Entry newEntry = mDBApi.putFile(path, input, file.length(), null, null);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
        return true;
    	}
    	public static File getFile(String path, File save){
            try {
    			AppKeyPair appKeys = new AppKeyPair(APP_KEY, APP_SECRET);
    			WebAuthSession session = new WebAuthSession(appKeys, ACCESS_TYPE);
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
    			AccessTokenPair tokens = new AccessTokenPair("l4yln3msdyua24o", "jf23d653v9cryms");
    			session.setAccessTokenPair(tokens);
    			mDBApi = new DropboxAPI<WebAuthSession>(session);
    			//ByteArrayInputStream inputStream = new ByteArrayInputStream(fileContents.getBytes());
    			FileOutputStream os = new FileOutputStream(save);
    			mDBApi.getFile(path, null, os, null);
    		} catch (Exception e) {
    			e.printStackTrace();
    			return null;
    		}
            return save;
        	}
    	public static void deleteFile(String path){
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
    			return;
    		}
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
