package com.useful.useful.utils;

import java.io.InputStream;
import java.io.OutputStream;
import java.security.NoSuchAlgorithmException;
import java.security.spec.AlgorithmParameterSpec;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

import com.useful.useful.useful;

public class Encrpyter {
	byte[] buf = new byte[1024];
	  Cipher ecipher;
	  Cipher dcipher;
	  public Encrpyter(SecretKey key) throws Exception{
	    byte[] iv = new byte[] { (byte) 0x8E, 0x12, 0x39, (byte) 0x9C, 0x07, 0x72, 0x6F, 0x5A };
	    AlgorithmParameterSpec paramSpec = new IvParameterSpec(iv);
	    ecipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
	    dcipher = Cipher.getInstance("DES/CBC/PKCS5Padding");

	    ecipher.init(Cipher.ENCRYPT_MODE, key, paramSpec);
	    dcipher.init(Cipher.DECRYPT_MODE, key, paramSpec);
}
	  public void encrypt(InputStream in, OutputStream out)  throws Exception{
		    out = new CipherOutputStream(out, ecipher);

		    int numRead = 0;
		    while ((numRead = in.read(buf)) >= 0) {
		      out.write(buf, 0, numRead);
		    }
		    out.close();
		  }

		  public InputStream decrypt(InputStream in)  throws Exception{
		    in = new CipherInputStream(in, dcipher);
            return in;
		  }
		  public Boolean generateNewKey(){
			  try {
				SecretKey key = KeyGenerator.getInstance("DES").generateKey();
				byte[] iv = new byte[] { (byte) 0x8E, 0x12, 0x39, (byte) 0x9C, 0x07, 0x72, 0x6F, 0x5A };
			    AlgorithmParameterSpec paramSpec = new IvParameterSpec(iv);
			    ecipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
			    dcipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
				ecipher.init(Cipher.ENCRYPT_MODE, key, paramSpec);
			    dcipher.init(Cipher.DECRYPT_MODE, key, paramSpec);
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
			  return true;
		  }
}
