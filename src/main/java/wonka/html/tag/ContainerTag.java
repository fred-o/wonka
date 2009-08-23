package wonka.html.tag;

public abstract class ContainerTag extends AbstractTag {

	protected ContainerTag(String tagName, Attributes attributes, Tag... children) {
		super(tagName, attributes, children);
	}

	protected ContainerTag(String tagName, Attributes attributes, Iterable<Tag> children) {
		super(tagName, attributes, children);
	}

	public boolean isOpen() {
		return true;
	}
}
