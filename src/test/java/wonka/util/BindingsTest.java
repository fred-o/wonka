package wonka.util;

import java.util.*;
import junit.framework.*;

public class BindingsTest extends TestCase {
    public void testSetAndGet() throws Exception {
	    Bindings<String, String> b1 = new Bindings<String, String>("I HAS", "CHEEZBURGER", null);
		assertEquals("CHEEZBURGER", b1.get("I HAS"));
		assertEquals(null, b1.get("OH HAI"));
	    Bindings<String, String> b2 = b1.set("I HAS", "BUKKIT");
		assertEquals("CHEEZBURGER", b1.get("I HAS"));
		assertEquals(null, b1.get("OH HAI"));
		assertEquals("BUKKIT", b2.get("I HAS"));
		assertEquals(null, b2.get("OH HAI"));
	    Bindings<String, String> b3 = b2.set("ORLY", "YARLY");
		assertEquals("CHEEZBURGER", b1.get("I HAS"));
		assertEquals(null, b1.get("OH HAI"));
		assertEquals("BUKKIT", b2.get("I HAS"));
		assertEquals(null, b2.get("OH HAI"));
		assertEquals("BUKKIT", b3.get("I HAS"));
		assertEquals(null, b3.get("OH HAI"));
		assertEquals("YARLY", b3.get("ORLY"));
	}

	public void testIterate() throws Exception {
	    Bindings<String, String> b = new Bindings<String, String>("I HAS", "CHEEZBURGER").set("ORLY", "YARLY");
		Iterator<Pair<String, String>> it = b.iterator();
		assertNotNull(it);
		assertTrue(it.hasNext());
		Pair<String, String> p1 = it.next();
		assertEquals("I HAS", p1.name);
		assertEquals("CHEEZBURGER", p1.value);
		assertTrue(it.hasNext());
		Pair<String, String> p2 = it.next();
		assertEquals("ORLY", p2.name);
		assertEquals("YARLY", p2.value);
		assertFalse(it.hasNext());
		try {
			it.next();
			fail();
		}
		catch (NoSuchElementException nsee) {
		}
	}

	public void testIterateWithDuplicates() throws Exception {
	    Bindings<String, String> b = new Bindings<String, String>("I HAS", "CHEEZBURGER").set("ORLY", "YARLY").set("I HAS", "BUKKIT");
		Iterator<Pair<String, String>> it = b.iterator();
		assertNotNull(it);
		assertTrue(it.hasNext());
		Pair<String, String> p1 = it.next();
		assertEquals("ORLY", p1.name);
		assertEquals("YARLY", p1.value);
		assertTrue(it.hasNext());
		Pair<String, String> p2 = it.next();
		assertEquals("I HAS", p2.name);
		assertEquals("BUKKIT", p2.value);
		assertFalse(it.hasNext());
		try {
			it.next();
			fail();
		}
		catch (NoSuchElementException nsee) {
		}
	}



}