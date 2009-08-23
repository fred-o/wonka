package wonka.server;

import java.io.IOException;
import java.nio.*;
import wonka.html.page.Page;
import wonka.html.page.StaticPage;
import wonka.html.render.*;
import wonka.html.tag.Tag;

public class SimpleResponse extends AbstractResponse {
    private final Page page;

	public SimpleResponse(Tag tag) {
		this(new StaticPage(tag));
	}

	public SimpleResponse(Page page) {
		this(ResponseCode.OK, page);
	}

	public SimpleResponse(ResponseCode responseCode, Page page) {
		super(responseCode);
		addHeader(Header.CONTENT_TYPE, "text/html");
		this.page = page;
	}

	@Override 
	protected byte[] renderResponse() {
		return new PageRenderer(page).render();
	}

}
