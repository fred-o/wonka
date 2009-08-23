package wonka.html.tag;

/**
 * Static helper methods for {@link Attributes} creation.
 * 
 * @author fredrik
 */
public class Attribute {
	private Attribute() {
	}

	public static Attributes id(String id) {
		return new Attributes("id", id);
	}

	public static Attributes clazz(String clazz) {
		return new Attributes("class", clazz);
	}

	public static Attributes style(String style) {
		return new Attributes("style", style);
	}

	public static Attributes attribute(String name, String value) {
		return new Attributes(name, value);
	}
}
