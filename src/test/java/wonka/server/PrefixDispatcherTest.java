package wonka.server;

import junit.framework.TestCase;
import wonka.html.page.*;
import wonka.html.tag.*;
import se.prb.cheesymock.*;

public class PrefixDispatcherTest extends TestCase {

	public void testPrefixMatches() throws Exception {
		StaticPage p = new StaticPage(new Html());
		Dispatcher d = new PrefixDispatcher("/test", p);
		Request request = CheesyMock.createMock(Request.class, new Object() {
			String path = "/test/monkey";
		});
		Response response = d.dispatch(request);
		assertNotNull(response);
	}

	public void testPrefixDoesntMatch() throws Exception {
		StaticPage p = new StaticPage(new Html());
		Dispatcher d = new PrefixDispatcher("/test", p);
		Request request = CheesyMock.createMock(Request.class, new Object() {
			String path = "/monkey/test";
		});
		Response response = d.dispatch(request);
		assertNull(response);
	}
}
