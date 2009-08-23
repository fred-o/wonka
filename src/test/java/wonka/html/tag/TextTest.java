package wonka.html.tag;

import wonka.html.render.*;
import junit.framework.*;
import static wonka.html.tag.Tags.*;

public class TextTest extends TestCase {

	public void testZeroStrings() {
		Tag t = text();
		assertEquals("", new String(new TagRenderer(t).render()));
	}

	public void testOneString() {
		Tag t = text("OH HAI!");
		assertEquals("OH HAI!", new String(new TagRenderer(t).render()));
	}

	public void testMultipleStrings() {
		Tag t = text("ORLY?", "YARLY!", "WTF?");
		assertEquals("ORLY?YARLY!WTF?", new String(new TagRenderer(t).render()));
	}
}