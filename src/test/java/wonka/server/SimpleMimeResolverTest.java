package wonka.server;

import java.io.*;
import junit.framework.*;

public class SimpleMimeResolverTest extends TestCase {

	public void testResolve() throws Exception {
	    MimeResolver r = new SimpleMimeResolver();
		assertEquals("text/html", r.getMimeType(new File("hello.html")));
		assertEquals("text/plain", r.getMimeType(new File("hello.txt")));
		assertEquals("text/css", r.getMimeType(new File("hello.css")));
		assertEquals("text/javascript", r.getMimeType(new File("hello.js")));
		assertEquals("image/jpeg", r.getMimeType(new File("hello.jpg")));
		assertEquals("image/jpeg", r.getMimeType(new File("hello.jpeg")));
		assertEquals("image/gif", r.getMimeType(new File("hello.gif")));
	}

	public void testResolveUpperCase() throws Exception {
	    MimeResolver r = new SimpleMimeResolver();
		assertEquals("text/html", r.getMimeType(new File("HELLO.HTML")));
		assertEquals("text/plain", r.getMimeType(new File("HELLO.TXT")));
		assertEquals("text/css", r.getMimeType(new File("HELLO.CSS")));
		assertEquals("text/javascript", r.getMimeType(new File("HELLO.JS")));
		assertEquals("image/jpeg", r.getMimeType(new File("HELLO.JPG")));
		assertEquals("image/jpeg", r.getMimeType(new File("HELLO.JPEG")));
		assertEquals("image/gif", r.getMimeType(new File("HELLO.GIF")));
	}

	public void testResolveNull() throws Exception {
	    MimeResolver r = new SimpleMimeResolver();
		try {
		    r.getMimeType(null);
			fail();
		}
		catch (NullPointerException npe) {
		}
	}
    
}