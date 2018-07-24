package com.employeedb.security;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

public class CipherManager {
	private static final String KEY = "6Uy89lkjj3i5Las3";
    private static final String ALGORITHM = "AES";
	
	
	public static String encrypt(String source) {
		String rtn = "";
		try {
			Cipher cipher = Cipher.getInstance(ALGORITHM);
	        cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(KEY.getBytes(), ALGORITHM));
	        rtn = new String(Base64.getEncoder().encode(cipher.doFinal(source.getBytes())));
		} catch(NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException e) {
			e.printStackTrace();
		}
		return rtn;
        
    }
    
    public static String decrypt(String encryptSource) {
    	String rtn = "";
    	try {
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(KEY.getBytes(), ALGORITHM));
            rtn = new String(cipher.doFinal(Base64.getDecoder().decode(encryptSource.getBytes())));
    	} catch(NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException e) {
    		e.printStackTrace();
    	}
    	return rtn;

    }
}
