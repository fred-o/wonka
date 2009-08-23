package wonka.server;

public interface DispatcherChain {
	public Response dispatch(Request request);
}
