package wonka.html.page;

public enum Preamble {
	HTML4(""), XHTML("");
	private String value;

	private Preamble(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
