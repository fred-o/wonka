package wonka.html.render;

import java.nio.*;
import wonka.html.page.*;
import wonka.html.tag.*;

public class PageRenderer {
	private Page page;
	private CharBuffer buffer;

	public PageRenderer(Page page) {
		this.page = page;
	}
	
	public byte[] render() {
		if (page != null) {
			return new TagRenderer(page.getRoot()).render();
		}
		else {
			return new byte[0];
		}
	}
}