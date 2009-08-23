package wonka.server;

import java.io.*;

/**
 * Utility class for creating {@link Dispatcher}s.
 */
public class Dispatchers {
	private Dispatchers() {
	}

	public static Dispatcher prefix(String prefix, Action action) {
		return new PrefixDispatcher(prefix, action);
	}

	public static Dispatcher dir(String prefix, File directory) throws IOException {
		return new PrefixDispatcher(prefix, new DirectoryAction(prefix, directory)); 
	}
}
