package wonka.html.tag;

import wonka.html.render.*;
import static wonka.html.tag.Attribute.*;
import static wonka.html.tag.Tags.*;
import junit.framework.TestCase;

public class DivTest extends TestCase {
	public void testNestedTags() {
		Div d = new Div(new P(text("lite text")), text("lite mer text"),
				new Div(id("innerDiv"), text("ännu mera text")));
		assertEquals("<div><p>lite text</p>lite mer text<div id=\"innerDiv\">ännu mera text</div></div>", new String(new TagRenderer(d).render()));
	}
}
