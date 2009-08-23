package wonka.html.tag;

import wonka.util.*;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

/**
 * This class represents a collection of Attributes.
 * 
 * @author fredrik
 */
public class Attributes extends Bindings<String, String> {

	public Attributes(String name, String value) {
		super(name, value);
	}

	public Attributes(String name, String value, Attributes next) {
		super(name, value, next);
	}

	public Attributes add(String name, String value) {
		return new Attributes(name, value, this);
	}

	public Attributes id(String id) {
		return new Attributes("id", id, this);
	}

	public Attributes clazz(String clazz) {
		return new Attributes("class", clazz, this);
	}

	public Attributes style(String style) {
		return new Attributes("stye", style, this);
	}
}
