package wonka.server;

import static wonka.html.tag.Tags.*;

import java.util.List;
import java.util.LinkedList;

import org.apache.log4j.Logger;

import wonka.html.page.StaticPage;
import wonka.html.tag.Body;
import wonka.html.tag.Html;

public class SimpleDispatcherChain implements DispatcherChain {
	private static final Logger LOG = Logger.getLogger(SimpleDispatcherChain.class);
	private List<Dispatcher> dispatchers = new LinkedList<Dispatcher>();

	public Response dispatch(Request request) {
		for (Dispatcher dispatcher : dispatchers) {
			Response response = dispatcher.dispatch(request);
			if (response != null) {
				return response;
			}
		}
		return ErrorResponse.forCode(ResponseCode.NOT_FOUND);
	}

	public void add(Dispatcher dispatcher) {
		dispatchers.add(dispatcher);
	}
}
