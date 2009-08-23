package wonka.server;

import java.io.*;
import org.apache.log4j.Logger;

/**
 * This {@link Action} serves static resources from a directory.
 */
public class DirectoryAction implements Action {
	private static final Logger LOG = Logger.getLogger(DirectoryAction.class);
	private String prefix;
	private File directory;
	private String directoryPath;
	private MimeResolver mimeResolver = new SimpleMimeResolver();

	public DirectoryAction(File directory) throws IOException {
		this(null, directory);
	}

	public DirectoryAction(String prefix, File directory) throws IOException {
		this.prefix = prefix;
		this.directory = directory;
		this.directoryPath = directory.getCanonicalPath();
	}

	public Response act(Request request) {
		try {
			String path = request.getPath();
			if (prefix != null) {
				path = path.substring(prefix.length());
			}
			File resource = new File(directory, path);
			if (!resource.getCanonicalPath().startsWith(directoryPath)) {
				return ErrorResponse.forCode(ResponseCode.NOT_FOUND);
			}
			int size = (int)resource.length();
			byte bytes[] = new byte[size];
			FileInputStream fis = new FileInputStream(resource);
			try {
				fis.read(bytes, 0, size);
				ByteArrayResponse response = new ByteArrayResponse(bytes);
				response.addHeader(Header.CONTENT_TYPE, mimeResolver.getMimeType(resource));
				return response;
			}
			finally {
				fis.close();
			}
		}
		catch (IOException ioe) {
			LOG.error("Error serving resource:", ioe);
			return ErrorResponse.forCode(ResponseCode.NOT_FOUND);
		}
	}

	public void setMimeResolver(MimeResolver mimeResolver) {
	    this.mimeResolver = mimeResolver;
	}
    
}
