package wonka.html.tag;

import java.util.*;
import junit.framework.*;
import static wonka.html.tag.Tags.*;

public class TagWrapperTest extends TestCase {
    public void testAddSomeElements() throws Exception {
	    TagWrapper l = new TagWrapper();
		l.and(new P()).and("OH HAI").and(new Tag[] { text("ORLY"), text("YARLY") });
		Iterator<Tag> it = l.iterator();
		assertEquals(P.class, it.next().getClass());
		assertEquals(Text.class, it.next().getClass());
		assertEquals(Text.class, it.next().getClass());
		assertEquals(Text.class, it.next().getClass());
		assertFalse(it.hasNext());
	}

	public void testAddAListWithElements() throws Exception {
	    TagWrapper l1 = new TagWrapper();
		List<Tag> l2 = Arrays.asList(new Tag[] { new P(), new P() });
		l1.and(l2);
		Iterator<Tag> it = l1.iterator();
		assertEquals(P.class, it.next().getClass());
		assertEquals(P.class, it.next().getClass());
		assertFalse(it.hasNext());
	}

	public void testAddAndWrapAListWithElements() throws Exception {
	    TagWrapper l1 = new TagWrapper();
		List<Tag> l2 = Arrays.asList(new Tag[] { new P(), new P() });
		l1.and(l2).in(Div.class);
		Iterator<Tag> it = l1.iterator();
		assertEquals(Div.class, it.next().getClass());
		assertEquals(Div.class, it.next().getClass());
		assertFalse(it.hasNext());
	}

}