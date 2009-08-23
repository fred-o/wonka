package wonka.server;

import java.io.IOException;
import java.nio.channels.WritableByteChannel;

public interface Response {
	public ResponseCode getResponseCode();

	public void sendResponse(WritableByteChannel channel) throws IOException;
}
