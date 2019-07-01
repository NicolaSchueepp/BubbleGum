package ch.bbcag.bubblegum.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

import javax.inject.Inject;

import ch.bbcag.bubblegum.util.encode.BCrypt;
import ch.bbcag.bubblegum.util.encode.EncodingMethod;
import ch.bbcag.bubblegum.util.encode.RandomEncoder;

public class Util {

	public static String encode(String password) {
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
		return UUID.randomUUID().toString() +"-"+ UUID.nameUUIDFromBytes(Util.encode(seed).getBytes()).toString();
	}
}
