package ch.bbcag.bubblegum.util.encode;

import javax.inject.Inject;

public class RandomEncoder implements EncodingMethod{

	
	
	@Override
	public String encode(String text) {
		EncodingMethod encodingMethod = null;
		switch ((text.length()%3)*10) {
		case 0:
			encodingMethod = new EncodingMethod1();
			break;
		case 3:
			encodingMethod = new EncodingMethod2();
			break;
		case 6:
			encodingMethod = new EncodingMethod3();
			break;
		}
		return encodingMethod.encode(text);
	}

}
