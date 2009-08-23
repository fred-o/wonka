package wonka.server;

import org.apache.log4j.Logger;
import wonka.html.page.*;

public class PrefixDispatcher implements Dispatcher {
	private static final Logger LOG = Logger.getLogger(PrefixDispatcher.class);
    private final String prefix;
	private final Action action;;

	public PrefixDispatcher(String prefix, Action action) {
		this.prefix = prefix;
		this.action = action;
	}

	public Response dispatch(Request request) {
		LOG.debug("Comparing prefix " + prefix + " with path " + request.getPath());
		if (request.getPath().startsWith(prefix)) {
			return action.act(request);
		}
		return null;
	}
}
