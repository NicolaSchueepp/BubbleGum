package ch.bbcag.bubblegum.util.encode;

public class EncodingMethod1 implements EncodingMethod{

	@Override
	public String encode(String text) {
		String secretAddon = "very-secret-text-this-is-si-sith-txet-terces-yrev";
		String newText = "";
		for(int i = 0; i < text.length(); i++) {
			int c = text.charAt(i);
			int secretChar = secretAddon.getBytes()[i>=secretAddon.length()?i-secretAddon.length():i];
			newText = newText + ((c*text.length()+secretChar)/(i+1)) + (i % 6 == 0 ? "-" : "a");
		}
		return newText;
	}

}
