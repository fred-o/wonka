package wonka.html.tag;

import wonka.html.render.*;
import junit.framework.TestCase;

import static wonka.html.tag.Attribute.*;
import static wonka.html.tag.Tags.*;

public class HtmlTest extends TestCase {
	public void testRenderHtmlTag1() {
		Html html = new Html(text("this is my body"));
		assertEquals("<html>this is my body</html>", new String(new TagRenderer(html).render()));
	}

	public void testRenderHtmlWithNoChildren() {
		Html html = new Html();
		assertEquals("<html></html>", new String(new TagRenderer(html).render()));
	}

	public void testRenderWithSeveralChildren() {
		Tag h = new Html(
			new Head(
				new Link(attribute("rel", "stylesheet")
						 .add("href", "/dir/css/style.css")
						 .add("type", "text/css"))),
			new Body(new P(text("some text"))));
		assertEquals("<html><head><link rel=\"stylesheet\" href=\"/dir/css/style.css\" type=\"text/css\" /></head><body><p>some text</p></body></html>", 
					 new String(new TagRenderer(h).render()));
	}
}
