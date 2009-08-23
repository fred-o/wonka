package wonka.html.tag;

public class P extends ContainerTag {
	public P(String text) {
		super("p", null, new Text(text));
	}

	public P(Tag... children) {
		super("p", null, children);
	}

	public P(Iterable<Tag> children) {
		super("p", null, children);
	}

	public P(Attributes attributes, Iterable<Tag> children) {
		super("p", attributes, children);
	}

	public P(Attributes attributes, Tag... children) {
		super("p", attributes, children);
	}
}
