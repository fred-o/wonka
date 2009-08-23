package wonka.server;

import java.io.*;
import java.util.*;
import java.util.regex.*;

public class SimpleMimeResolver implements MimeResolver {
	private static Map<String, String> mimeTypes = new HashMap<String, String>();
	static {
		mimeTypes.put("html", "text/html");
		mimeTypes.put("txt", "text/plain");
		mimeTypes.put("css", "text/css");
		mimeTypes.put("js", "text/javascript");
		mimeTypes.put("jpg", "image/jpeg");
		mimeTypes.put("jpeg", "image/jpeg");
		mimeTypes.put("gif", "image/gif");
	}
	private static final Pattern FILENAME = Pattern.compile(".*\\.(\\w+)");
    
	public String getMimeType(File file) {
		Matcher m = FILENAME.matcher(file.getName());
		if (m.matches()) {
		    return mimeTypes.get(m.group(1).toLowerCase());
		}
		return null;
	}

}