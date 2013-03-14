package com.useful.useful.utils;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.security.spec.AlgorithmParameterSpec;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

public class Encrpyter {
	byte[] buf = new byte[1024];
	  Cipher ecipher;
	  Cipher dcipher;
	  Cipher secipher;
	  Cipher sdcipher;
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
		public String encryptStr(String str) {
		        try {
		            // Encode the string into bytes using utf-8
		            byte[] utf8 = str.getBytes("UTF8");

		            // Encrypt
		            byte[] enc = ecipher.doFinal(utf8);

		            // Encode bytes to base64 to get a string
		            return new sun.misc.BASE64Encoder().encode(enc);
		        } catch (javax.crypto.BadPaddingException e) {
		        } catch (IllegalBlockSizeException e) {
		        } catch (UnsupportedEncodingException e) {
		        } catch (@SuppressWarnings("hiding") java.io.IOException e) {
		        }
		        return null;
		    }

		    public String decryptStr(String str) {
		        try {
		            // Decode base64 to get bytes
		            byte[] dec = new sun.misc.BASE64Decoder().decodeBuffer(str);

		            // Decrypt
		            byte[] utf8 = dcipher.doFinal(dec);

		            // Decode using utf-8
		            return new String(utf8, "UTF8");
		        } catch (javax.crypto.BadPaddingException e) {
		        } catch (IllegalBlockSizeException e) {
		        } catch (UnsupportedEncodingException e) {
		        } catch (java.io.IOException e) {
		        }
		        return null;
		    }
}
