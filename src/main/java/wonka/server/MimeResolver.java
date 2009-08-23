package wonka.server;

import java.io.*;

public interface MimeResolver {
    
	/**
	 * Return the MIME type for a given file.
	 */
	public String getMimeType(File file);
}