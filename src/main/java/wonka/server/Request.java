package wonka.server;

import java.util.Map;

public interface Request {
	public String getHeader(Header header);

	public String getPath();

	public Map<String, String> getParameters();

	public Method getMethod();
}
