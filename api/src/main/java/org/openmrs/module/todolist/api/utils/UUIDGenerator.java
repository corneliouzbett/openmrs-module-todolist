package org.openmrs.module.todolist.api.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

public class UUIDGenerator {
	
	private static final char[] hexArray = "0123456789ABCDEF".toCharArray();
	
	public static String generateUUID() throws NoSuchAlgorithmException {
		MessageDigest salt = MessageDigest.getInstance("SHA-256");
		try {
			salt.update(UUID.randomUUID().toString().getBytes("UTF-8"));
		}
		catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		String digest = bytesToHex(salt.digest());
		return digest;
	}
	
	public static String bytesToHex(byte[] bytes) {
		char[] hexChars = new char[bytes.length * 2];
		for (int j = 0; j < bytes.length; j++) {
			int v = bytes[j] & 0xFF;
			hexChars[j * 2] = hexArray[v >>> 4];
			hexChars[j * 2 + 1] = hexArray[v & 0x0F];
		}
		return new String(hexChars);
	}
}
