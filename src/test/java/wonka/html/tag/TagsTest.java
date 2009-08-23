package wonka.html.tag;

import wonka.html.render.*;
import static wonka.html.tag.Tags.*;
import junit.framework.*;

public class TagsTest extends TestCase {

	public void testStylesheet() throws Exception {
		Tag l = stylesheet("css/style.css");
		assertEquals("<link href=\"css/style.css\" rel=\"stylesheet\" type=\"text/css\" />", new String(new TagRenderer(l).render()));
	}

}