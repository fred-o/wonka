package wonka.html.tag;

public class Body extends ContainerTag {
	public Body(Tag... children) {
		this(null, children);
	}

	public Body(Iterable<Tag> children) {
		super("body", null, children);
	}

	public Body(Attributes attributes, Tag... children) {
		super("body", attributes, children);
	}
}
