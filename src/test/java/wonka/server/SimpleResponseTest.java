package wonka.server;

import java.io.ByteArrayOutputStream;
import java.nio.channels.Channels;
import java.nio.channels.WritableByteChannel;

import junit.framework.TestCase;
import wonka.html.page.StaticPage;
import wonka.html.tag.Html;

public class SimpleResponseTest extends TestCase {
	public void testRender404Response() throws Exception {
		SimpleResponse response = new SimpleResponse(ResponseCode.NOT_FOUND, null);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		WritableByteChannel channel = Channels.newChannel(baos);
		response.sendResponse(channel);
		assertEquals("HTTP/1.1 404 \nContent-Length: 0\nContent-Type: text/html\n\n", new String(baos.toByteArray()));
	}

	public void testRenderHtmlResponse() throws Exception {
		SimpleResponse response = new SimpleResponse(ResponseCode.OK, new StaticPage(new Html()));
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		WritableByteChannel channel = Channels.newChannel(baos);
		response.sendResponse(channel);
		assertEquals("HTTP/1.1 200 \nContent-Length: 13\nContent-Type: text/html\n\n<html></html>", new String(baos.toByteArray()));
	} 
}
