package wonka.util;

import junit.framework.TestCase;

public class PairTest extends TestCase {
	public void testCreatePair() {
		Pair<String, String> p1 = new Pair<String, String>("hej", "hopp");
		assertEquals("hej", p1.name);
		assertEquals("hopp", p1.value);
	}
}
