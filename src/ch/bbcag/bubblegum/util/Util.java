package ch.bbcag.bubblegum.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

import javax.inject.Inject;

import ch.bbcag.bubblegum.util.encode.EncodingMethod;
import ch.bbcag.bubblegum.util.encode.RandomEncoder;

public class Util {

	public static String encode(String text) {
		EncodingMethod encoder = new RandomEncoder();
		byte[] encodedhash;
		MessageDigest digest;
		try {
			text = encoder.encode(text);
			digest = MessageDigest.getInstance("SHA-256");
			encodedhash = digest.digest((text+"baum, ich mag züge, drölf, i like tutles, semickolon, alk, absinth, züri !grösser als graubünda").getBytes());
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
		return new String(encodedhash);
	}

	public static String generateKey(String seed) {
		return UUID.randomUUID().toString() +"-"+ UUID.nameUUIDFromBytes(Util.encode(seed).getBytes()).toString();
	}
}
