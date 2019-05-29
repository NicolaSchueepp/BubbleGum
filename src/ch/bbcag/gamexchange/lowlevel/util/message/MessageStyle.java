package ch.bbcag.gamexchange.lowlevel.util.message;

public enum MessageStyle {

	Info("<div class=\"alert alert-success\" role=\"alert\">\n",
			"</div>\n"), 
	Warning("<div class=\"alert alert-warning\" role=\"alert\">\n",
			"</div>"), 
	error("<div class=\"alert alert-danger\" role=\"alert\">\n",
			"</div>\n");
	
	
	private String header;
	private String footer;
	
	private MessageStyle(String header, String footer) {
		this.header = header;
		this.footer = footer;
	}
	
	public String insert(String text) {
		return header + text + footer;
	}
	
}
