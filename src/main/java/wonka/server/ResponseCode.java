package wonka.server;

public enum ResponseCode {
	OK(200, "Ok"), 
		BAD_REQUEST(400, "Bad Requst"), 
		FORBIDDEN(400, "Forbiddeon"),
		NOT_FOUND(404, "Not Found"), 
		METHOD_NOR_ALLOWED(405, "Method not Allowed/Implemented"), 
		INTERNAL_SERVER_ERROR(500, "Internal Server Error");

	private final int code;
	private final String message;

	private ResponseCode(int code, String message) {
		this.code = code;
		this.message = message;
	}

	public int getCode() {
		return code;
	}

	public String getMessage() {
	    return message;
	}
}
