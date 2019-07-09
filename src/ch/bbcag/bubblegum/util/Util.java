package ch.bbcag.bubblegum.util;

import java.util.UUID;

import ch.bbcag.bubblegum.util.encode.BCrypt;
import ch.bbcag.bubblegum.util.encode.EncodingMethod;
import ch.bbcag.bubblegum.util.encode.RandomEncoder;

public class Util {

	public static String encodePassword(String password) {
		EncodingMethod encoder = new RandomEncoder();
		password = encoder.encode(password);
		String salt = BCrypt.gensalt(12);
		String hashedPassword = BCrypt.hashpw(password, salt);
		return hashedPassword;
	}

	public static boolean verifyPassword(String password, String bCryptPassword) {
		EncodingMethod encoder = new RandomEncoder();
		return BCrypt.checkpw(encoder.encode(password), bCryptPassword);
	}
	
	public static String generateKey(String seed) {
		return UUID.randomUUID().toString() +"-"+ UUID.nameUUIDFromBytes((seed+UUID.randomUUID().toString()).getBytes()).toString();
	}
}
