package wonka.test;

import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class NIOTest {
	public static void main(String[] argv) throws Exception {
		FileInputStream fis = new FileInputStream("src/test/java/wonka/test/NIOTest.java");
		FileChannel fc = fis.getChannel();
		ByteBuffer buf = ByteBuffer.allocate(1024);
		fc.read(buf);
		for (int i = 0; i < buf.capacity(); i++) {
			System.out.print(new String(new byte[] { buf.get(i) }));
		}
	}
}
