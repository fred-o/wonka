package wonka.html.page;

import wonka.html.render.*;
import static wonka.html.tag.Attribute.*;
import static wonka.html.tag.Tags.*;
import junit.framework.TestCase;
import wonka.html.tag.Body;
import wonka.html.tag.Div;
import wonka.html.tag.Html;

public class StaticPageTest extends TestCase {
	public void testRenderPage() {
		Page p = new StaticPage(Preamble.XHTML, new Html(new Body(new Div(id("firstDiv"), text("some text")))));
		assertEquals("<html><body><div id=\"firstDiv\">some text</div></body></html>", 
					 new String(new PageRenderer(p).render()));
	}

	public void testRenderPageWithNullPreamble() {
		Page p = new StaticPage(null, new Html(new Body(new Div(id("firstDiv"), text("some text")))));
		assertEquals("<html><body><div id=\"firstDiv\">some text</div></body></html>", 
					 new String(new PageRenderer(p).render()));
	}

	public void testRenderPageWithNoPreamble() {
		Page p = new StaticPage(new Html(new Body(new Div(id("firstDiv"), text("some text")))));
		assertEquals("<html><body><div id=\"firstDiv\">some text</div></body></html>", 
					 new String(new PageRenderer(p).render()));
	}
}
