package wonka.server;

public class MethodNotSupportedException extends Exception {
	private String methodName;

	public MethodNotSupportedException(String methodName) {
		this.methodName = methodName;
	}

	public String getMethodName() {
		return methodName;
	}
}
