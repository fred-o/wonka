package wonka.server;

public class ByteArrayResponse extends AbstractResponse {
	private byte[] content;

	public ByteArrayResponse(byte[] content) {
		super(ResponseCode.OK);
		this.content = content;
	}

	@Override
	public byte[] renderResponse() {
		return content;
	}
}