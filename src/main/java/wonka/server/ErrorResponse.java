package wonka.server;

import java.nio.*;
import java.util.*;
import wonka.html.page.*;
import wonka.html.render.*;
import wonka.html.tag.*;
import static wonka.html.tag.Tags.*;

public class ErrorResponse extends AbstractResponse {
	private static final Map<ResponseCode, ErrorResponse> responses = 
		new EnumMap<ResponseCode, ErrorResponse>(ResponseCode.class);

	static {
		for(ResponseCode code: EnumSet.allOf(ResponseCode.class)) {
			responses.put(code, new ErrorResponse(code));
		}
	}

	private byte[] content;

	private ErrorResponse(ResponseCode responseCode) {
		super(responseCode);
		this.content = new PageRenderer(new StaticPage(
											new Html(
												new Body(
													text(responseCode.getCode() + " - " + 
														 responseCode.getMessage()))))).render();
	}

	@Override
	protected byte[] renderResponse() {
		return content;
	}

	public static ErrorResponse forCode(ResponseCode responseCode) {
		return responses.get(responseCode);
	}
}
