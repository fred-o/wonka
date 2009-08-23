package wonka.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.nio.*;
import java.nio.charset.*;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.log4j.*;

public class SimpleRequest implements Request {
	private static final Logger LOG = Logger.getLogger(SimpleRequest.class);
	private static final Pattern REQUEST_LINE = Pattern
		.compile("(OPTIONS|GET|HEAD|POST|PUT|DELETE|TRACE|CONNECT)\\s(\\S+)\\s(HTTP/1.1).*");
	private static final Pattern HEADER_LINE = Pattern.compile("(\\S*):\\s*(.*)");
	private static final Pattern PATH = Pattern.compile("([^?]*)(\\?(.*))?");
	private final Map<String, String> headers = new HashMap<String, String>(10);
	private final Map<String, String> parameters = new HashMap<String, String>(10);
	private Method method;
	private String path;

	public SimpleRequest(ByteBuffer bytes) throws IOException, MalformedRequestException,
		MethodNotSupportedException {
		bytes.rewind();
		CharBuffer cb = Charset.defaultCharset().newDecoder().decode(bytes);
		if (LOG.isDebugEnabled()) { LOG.debug("HTTP Request: " + cb.toString()); }
		BufferedReader reader = new BufferedReader(new StringReader(new String(bytes.array())));
		Matcher ml = REQUEST_LINE.matcher(reader.readLine());
		if (!ml.matches()) {
			throw new MalformedRequestException();
		}
		try {
			this.method = Method.valueOf(ml.group(1));
		} catch (IllegalArgumentException iae) {
			throw new MethodNotSupportedException(ml.group(1));
		}
		Matcher mp = PATH.matcher(ml.group(2));
		if(mp.matches()) {
			this.path = mp.group(1);
			if(mp.group(3) != null) {
				for(String pair: mp.group(3).split("&")) {
					String[] val = pair.split("=");
					if(val.length == 2) {
						parameters.put(val[0], val[1]);
					}
				}
			}
		}
		String line = null;
		while ((line = reader.readLine()) != null) {
			Matcher mh = HEADER_LINE.matcher(line);
			if (mh.matches()) {
				headers.put(mh.group(1), mh.group(2));
			}
		}
	}

	public String getHeader(Header header) {
		return headers.get(header.getHeader());
	}

	public String getPath() {
		return path;
	}
	
	public Map<String, String> getParameters() {
		return parameters;
	}

	public Method getMethod() {
		return method;
	}
}
