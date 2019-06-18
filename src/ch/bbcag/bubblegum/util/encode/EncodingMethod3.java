package ch.bbcag.bubblegum.util.encode;

public class EncodingMethod3 implements EncodingMethod {

	@Override
	public String encode(String text) {
		byte[] bytes = new byte[text.length()];
		for(int i = 0; i < text.length(); i++) {
			int c = text.charAt(i);
			bytes[i] = (byte) ((c*i) + ((char)c+i));
		}
		return new String(bytes);
	}

}
