package wonka.server;

import java.io.IOException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.WritableByteChannel;
import java.util.*;

public abstract class AbstractResponse implements Response {
	protected ResponseCode responseCode;
    protected Map<Header, String> headers = new TreeMap<Header, String>();

	public AbstractResponse(ResponseCode responseCode) {
		this.responseCode = responseCode;
	}

	public ResponseCode getResponseCode() {
	    return responseCode;
	}

    public void addHeader(Header header, String value) {
		headers.put(header, value);
	}

	public void sendResponse(WritableByteChannel channel) throws IOException {
		byte[] contentBytes = renderResponse(); 
		addHeader(Header.CONTENT_LENGTH, Integer.toString(contentBytes.length));
		StringBuilder builder = new StringBuilder();
		builder.append("HTTP/1.1 ").append(responseCode.getCode()).append(" \n");
		for(Map.Entry<Header, String> entry: headers.entrySet()) {
			builder.append(entry.getKey().getHeader()).append(": ").append(entry.getValue()).append("\n");
		}
		builder.append("\n");
		builder.append(new String(contentBytes));
		ByteBuffer buf = ByteBuffer.wrap(builder.toString().getBytes());
		channel.write(buf);
	}

	protected abstract byte[] renderResponse() throws IOException;

}
