package ch.bbcag.bubblegum.util.message;

public enum MessageStyle {

	Info("<div class=\"alert alert-success alert-dismissible fade show\" role=\"alert\">\n",
			"<button type=\"button\" class=\"close\" data-dismiss=\"alert\" aria-label=\"Close\">\r\n"
					+ "    <span aria-hidden=\"true\">&times;</span>\r\n" + "  </button></div>\n"),
	Warning("<div class=\"alert alert-warning alert-dismissible fade show\" role=\"alert\">\n",
			"<button type=\"button\" class=\"close\" data-dismiss=\"alert\" aria-label=\"Close\">\r\n"
					+ "    <span aria-hidden=\"true\">&times;</span>\r\n" + "  </button></div>"),
	error("<div class=\"alert alert-danger alert-dismissible fade show\" role=\"alert\">\n",
			"<button type=\"button\" class=\"close\" data-dismiss=\"alert\" aria-label=\"Close\">\r\n"
					+ "    <span aria-hidden=\"true\">&times;</span>\r\n" + "  </button></div>\n");

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
