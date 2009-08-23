package wonka.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.apache.log4j.Logger;

public class HttpServer {
	public static final Logger LOG = Logger.getLogger(HttpServer.class);
	private DispatcherChain dispatcherChain; 
	private List<InetSocketAddress> addresses = Arrays.asList(new InetSocketAddress(8080));
	private ExecutorService executor = Executors.newFixedThreadPool(4);

	public HttpServer() {
		Runtime.getRuntime().addShutdownHook(
			new Thread() {
				public void run() {
					LOG.info("Stopping server...");
				}
			});
	}

	public void run() throws IOException {
		LOG.info("Starting server...");
		Selector selector = Selector.open();
		ServerSocketChannel ssc = ServerSocketChannel.open();
		ssc.configureBlocking(false);
		ServerSocket socket = ssc.socket();
		for (InetSocketAddress address : addresses) {
			socket.bind(address);
		}
		ssc.register(selector, SelectionKey.OP_ACCEPT);
		while (true) {
			SelectionKey key = null;
			try {
				while (true) {
					selector.select();
					Iterator<SelectionKey> iter = selector.selectedKeys().iterator();
					while (iter.hasNext()) {
						key = iter.next();
						if (key.isAcceptable()) {
							ServerSocketChannel channel = (ServerSocketChannel) key.channel();
							SocketChannel sc = channel.accept();
							sc.configureBlocking(false);
							sc.register(selector, SelectionKey.OP_READ);
						} else if (key.isReadable()) {
							SocketChannel channel = (SocketChannel) key.channel();
							ByteBuffer buf = ByteBuffer.allocate(2048);
							channel.read(buf);
							if (LOG.isDebugEnabled()) {
								LOG.debug(new String(buf.array()));
							}
							executor.execute(new RequestHandler(key, buf, dispatcherChain));
						}
						iter.remove();
					}
				}
			} catch (IOException ioe) {
				LOG.error(ioe);
				key.cancel();
			}
		}
	}
	
	public void setDispatcherChain(DispatcherChain dispatcherChain) {
	    this.dispatcherChain = dispatcherChain;
	}
}
