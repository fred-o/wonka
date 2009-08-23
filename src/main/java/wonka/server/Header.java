package wonka.server;

public enum Header {
	ACCEPT("Accept"), 
		ACCEPT_LANGUAGE("Accept-Language"), 
		ACCEPT_CHARSET("Accept-Charset"), 
		ACCEPT_ENCODING("Accept-Encoding"), 
		CONTENT_LENGTH("Content-Length"), 
		CONTENT_TYPE("Content-Type"), 
		HOST("Host"), 
		KEEP_ALIVE("Keep-Alive"),
		REFERER("Referer"),
		USER_AGENT("User-Agent");
	private String header;

	private Header(String header) {
		this.header = header;
	}

	public String getHeader() {
		return header;
	}
}
