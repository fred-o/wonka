package wonka.server;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

import org.apache.log4j.Logger;

public class EchoServer {
	private static final Logger LOG = Logger.getLogger(EchoServer.class);

	public static void main(String[] argv) throws Exception {
		Selector selector = Selector.open();
		ServerSocketChannel ssc = ServerSocketChannel.open();
		ssc.configureBlocking(false);
		ServerSocket socket = ssc.socket();
		InetSocketAddress address = new InetSocketAddress(7000);
		socket.bind(address);
		SelectionKey key = ssc.register(selector, SelectionKey.OP_ACCEPT);
		while (true) {
			int num = selector.select();
			LOG.info(num + " new events.");
			Iterator<SelectionKey> it = selector.selectedKeys().iterator();
			while (it.hasNext()) {
				SelectionKey k = it.next();
				if ((k.readyOps() & SelectionKey.OP_ACCEPT) == SelectionKey.OP_ACCEPT) {
					ServerSocketChannel channel = (ServerSocketChannel) k.channel();
					SocketChannel sc = channel.accept();
					sc.configureBlocking(false);
					SelectionKey newKey = sc.register(selector, SelectionKey.OP_READ);
				} else if ((k.readyOps() & SelectionKey.OP_READ) == SelectionKey.OP_READ) {
					SocketChannel channel = (SocketChannel) k.channel();
					ByteBuffer buf = ByteBuffer.allocate(256);
					channel.read(buf);
					for (int i = 0; i < buf.capacity(); i++) {
						if (buf.get(i) == 4) {
							channel.close();
						} else {
							System.out.print(new String(new byte[] { buf.get(i) }));
						}
					}
					ByteBuffer outBuf = ByteBuffer.allocate(32);
					outBuf.put("OK\n".getBytes());
					outBuf.flip();
					channel.write(outBuf);
					outBuf.clear();
					channel.close();
					LOG.info("Read!");
				}
				it.remove();
			}
		}
	}
}
