package wonka.html.tag;

import static wonka.html.tag.Attribute.*;

/**
 * Convenience methods for tag manipulation.
 *
 * @author fredrik
 */
public class Tags {

    private Tags() {
	}

	public static TagWrapper wrap(Tag... tags) {
		TagWrapper list = new TagWrapper();
		return list.and(tags);
	}

	public static TagWrapper wrap(Iterable<Tag> tags) {
		TagWrapper list = new TagWrapper();
		return list.and(tags);
	}

	public static TagWrapper wrap(String... strings) {
		TagWrapper list = new TagWrapper();
		return list.and(strings);
	}

	public static TagWrapper list(Tag... tags) {
		TagWrapper list = new TagWrapper();
		return list.and(tags);
	}

	public static TagWrapper list(Iterable<Tag> tags) {
		TagWrapper list = new TagWrapper();
		return list.and(tags);
	}

	public static TagWrapper list(String... strings) {
		TagWrapper list = new TagWrapper();
		return list.and(strings);
	}

	public static Iterable<Tag> table(Object obj) {
		return null;
	} 

	public static Link stylesheet(String href) {
		return new Link(attribute("href", href).add("rel", "stylesheet").add("type", "text/css"));
	}

	public static Text text(String... text) {
		StringBuilder b = new StringBuilder();
		for(String s: text) {
			b.append(s);
		}
		return new Text(b.toString());
	}

}