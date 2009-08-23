package wonka.html.tag;

public class Div extends ContainerTag {
	public Div(Tag... children) {
		super("div", null, children);
	}

	public Div(Iterable<Tag> children) {
		super("div", null, children);
	}

	public Div(Attributes attributes, Iterable<Tag> children) {
		super("div", attributes, children);
	}

	public Div(Attributes attributes, Tag... children) {
		super("div", attributes, children);
	}
}
