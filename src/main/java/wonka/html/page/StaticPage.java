package wonka.html.page;

import wonka.html.render.*;
import java.nio.*;
import wonka.server.*;
import wonka.html.page.*;
import wonka.html.tag.Tag;

/**
 * This class represents a static page rendered using the HTML library. 
 * For convenience, it also implements the {@link Action} interface.
 */
public class StaticPage extends AbstractPage implements Action {
	private Tag rootTag;

	public StaticPage(Preamble preamble, Tag rootTag) {
		super(preamble);
		this.rootTag = rootTag;
	}

	public StaticPage(Tag rootTag) {
		this(null, rootTag);
	}

	public Tag getRoot() {
		return rootTag;
	}

	public Response act(Request request) {
		return new SimpleResponse(this);
	}
}
