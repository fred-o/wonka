package wonka.server;

import junit.framework.*;
import se.prb.cheesymock.*;
import java.io.*;

public class DirectoryActionTest extends TestCase {

    public void testTestGetExistingFile() throws Exception {
		Request req = CheesyMock.createMock(Request.class, new Object() {
			String path = "/hello1.html";
		});
		Action a = new DirectoryAction(new File("src/test/resources/html"));
		Response res = a.act(req);
		assertNotNull(res);
		assertEquals(ResponseCode.OK, res.getResponseCode());
	}

    public void testTestGetExistingFileInSubdir() throws Exception {
		Request req = CheesyMock.createMock(Request.class, new Object() {
			String path = "/sub/hello2.html";
		});
		Action a = new DirectoryAction(new File("src/test/resources/html"));
		Response res = a.act(req);
		assertNotNull(res);
		assertEquals(ResponseCode.OK, res.getResponseCode());
	}

    public void testTestGetNonExistingFile() throws Exception {
		Request req = CheesyMock.createMock(Request.class, new Object() {
			String path = "/wombat.html";
		});
		Action a = new DirectoryAction(new File("src/test/resources/html"));
		Response res = a.act(req);
		assertNotNull(res);
		assertEquals(ResponseCode.NOT_FOUND, res.getResponseCode());
	}

	public void testGetParentDirOK() throws Exception {
		Request req = CheesyMock.createMock(Request.class, new Object() {
			String path = "/sub/../hello1.html";
		});
		Action a = new DirectoryAction(new File("src/test/resources/html"));
		Response res = a.act(req);
		assertNotNull(res);
		assertEquals(ResponseCode.OK, res.getResponseCode());
	}

	public void testGetParentDirNotOK() throws Exception {
		Request req = CheesyMock.createMock(Request.class, new Object() {
			String path = "/../empty.file";
		});
		Action a = new DirectoryAction(new File("src/test/resources/html"));
		Response res = a.act(req);
		assertNotNull(res);
		assertEquals(ResponseCode.NOT_FOUND, res.getResponseCode());
	}

	public void testWithPrefixAndFileFound() throws Exception {
		Request req = CheesyMock.createMock(Request.class, new Object() {
			String path = "/virtual/hello1.html";
		});
		Action a = new DirectoryAction("/virtual", new File("src/test/resources/html"));
		Response res = a.act(req);
		assertNotNull(res);
		assertEquals(ResponseCode.OK, res.getResponseCode());
	}

}