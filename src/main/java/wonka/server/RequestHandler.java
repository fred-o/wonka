package wonka.server;

import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;

public class RequestHandler implements Runnable {
	private SelectionKey key;
	private ByteBuffer buf;
	private DispatcherChain dispatcherChain;

	public RequestHandler(SelectionKey key, ByteBuffer buf, DispatcherChain dispatcherChain) {
		this.key = key;
		this.buf = buf;
		this.dispatcherChain = dispatcherChain;
	}

	public void run() {
		HttpServer.LOG.info("Oh! Oooh! New request!");
		try {
			SocketChannel channel = (SocketChannel) key.channel();
			Response response = null;
			try {
				SimpleRequest request = new SimpleRequest(buf);
				response = dispatcherChain.dispatch(request);
			} catch (MalformedRequestException mre) {
				response = ErrorResponse.forCode(ResponseCode.BAD_REQUEST);
			} catch (MethodNotSupportedException mnse) {
				response = ErrorResponse.forCode(ResponseCode.METHOD_NOR_ALLOWED);
			}
			response.sendResponse(channel);
			channel.close();
		} catch (Exception ioe) {
			HttpServer.LOG.error("IO error: ", ioe);
		}
	}
}
