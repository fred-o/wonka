package wonka.server;

import java.nio.ByteBuffer;
import java.util.Map;
import junit.framework.TestCase;

public class SimpleRequestTest extends TestCase {
	public void testJucklo() throws Exception {
	    String p = "apa?bepa";
		String[] s = p.split("\\?");
		System.out.println(s[0]);
		System.out.println(s[1]);
	}

	public void testCreateRequestFromBuffer() throws Exception {
		ByteBuffer buf = ByteBuffer
				.wrap(("GET / HTTP/1.1\n"
						+ "Host: localhost:8080\n"
						+ "User-Agent: Mozilla/5.0 (X11; U; Linux i686; en-US; rv:1.9.0.1) Gecko/2008072820 Firefox/3.0.1\n"
						+ "Accept: text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8\n"
						+ "Accept-Language: sv,en-us;q=0.7,en;q=0.3\n" + "Accept-Encoding: gzip,deflate\n"
						+ "Accept-Charset: ISO-8859-1,utf-8;q=0.7,*;q=0.7\n" + "Keep-Alive: 300\n"
						+ "Connection: keep-alive\n").getBytes());
		Request request = new SimpleRequest(buf);
		assertEquals("/", request.getPath());
		assertEquals(Method.GET, request.getMethod());
		assertEquals("localhost:8080", request.getHeader(Header.HOST));
		assertEquals("Mozilla/5.0 (X11; U; Linux i686; en-US; rv:1.9.0.1) Gecko/2008072820 Firefox/3.0.1", request
				.getHeader(Header.USER_AGENT));
		assertEquals("ISO-8859-1,utf-8;q=0.7,*;q=0.7", request.getHeader(Header.ACCEPT_CHARSET));
		assertNotNull(request.getParameters());
		assertEquals(0, request.getParameters().size());
	}

	public void testWithGetParameters() throws Exception {
		ByteBuffer buf = ByteBuffer
				.wrap(("GET /?i_has=cheezburger&is=nice HTTP/1.1\n"
						+ "Host: localhost:8080\n"
						+ "User-Agent: Mozilla/5.0 (X11; U; Linux i686; en-US; rv:1.9.0.1) Gecko/2008072820 Firefox/3.0.1\n"
						+ "Accept: text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8\n"
						+ "Accept-Language: sv,en-us;q=0.7,en;q=0.3\n" + "Accept-Encoding: gzip,deflate\n"
						+ "Accept-Charset: ISO-8859-1,utf-8;q=0.7,*;q=0.7\n" + "Keep-Alive: 300\n"
						+ "Connection: keep-alive\n").getBytes());
		Request request = new SimpleRequest(buf);
		assertEquals("/", request.getPath());
		Map<String, String> p = request.getParameters();
		assertNotNull(p);
		assertEquals(2, p.size());
		assertEquals("cheezburger", p.get("i_has"));
		assertEquals("nice", p.get("is"));
	}
}
