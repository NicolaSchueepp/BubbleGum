package ch.bbcag.bubblegum.util.encode;

public class RandomEncoder implements EncodingMethod{

	
	@Override
	public String encode(String text) {
		EncodingMethod encodingMethod = null;
		switch (text.length()%3) {
		case 0:
			encodingMethod = new EncodingMethod1();
			break;
		case 1:
			text = text + "ab";
			encodingMethod = new EncodingMethod2();
			break;
		case 2:
			text = text + "a";
			encodingMethod = new EncodingMethod3();
			break;
		}
		return encodingMethod.encode(text);
	}

}
