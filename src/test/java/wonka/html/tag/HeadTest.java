package wonka.html.tag;

import wonka.html.render.*;
import junit.framework.*;

import static wonka.html.tag.Attribute.*;

public class HeadTest extends TestCase {
	
	public void testRenderWithLink() {
		Tag h = new Head(
			new Link(attribute("rel", "stylesheet")
					 .add("href", "/dir/css/style.css")
					 .add("type", "text/css")));
		assertEquals("<head><link rel=\"stylesheet\" href=\"/dir/css/style.css\" type=\"text/css\" /></head>", new String(new TagRenderer(h).render()));
	}

}