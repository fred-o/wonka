package wonka.html.tag;

public class LeafTag extends AbstractTag {
	
	protected LeafTag(String tagName, Attributes attributes) {
		super(tagName, attributes, (Tag[])null);
	}

	public boolean isOpen() {
		return false;
	}
}