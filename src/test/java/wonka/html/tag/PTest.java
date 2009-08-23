package wonka.html.tag;

import wonka.html.render.*;
import static wonka.html.tag.Attribute.id;
import static wonka.html.tag.Tags.*;
import junit.framework.TestCase;

public class PTest extends TestCase {
	public void testRenderPWIthAttributes() {
		P p = new P(id("alfa").clazz("myClass"), text("a bit of text"));
		assertEquals("<p id=\"alfa\" class=\"myClass\">a bit of text</p>", new String(new TagRenderer(p).render()));
	}
}
