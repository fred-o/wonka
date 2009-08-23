package wonka.html.tag;

import java.util.*;

public abstract class AbstractTag implements Tag {
	protected final String tagName;
	protected final Attributes attributes;
	protected final List<Tag> children;

	protected AbstractTag(String tagName, Attributes attributes, Tag... children) {
		this.tagName = tagName;
		this.attributes = attributes;
		if(children != null) {
			this.children = Arrays.asList(children);
		}
		else {
			this.children = Collections.emptyList();
		}
	}

	protected AbstractTag(String tagName, Attributes attributes, Iterable<Tag> children) {
		this.tagName = tagName;
		this.attributes = attributes;
		List<Tag> tmp = new LinkedList<Tag>();
		for(Tag child: children) {
			tmp.add(child);
		}
		if(children != null) {
			this.children = Collections.unmodifiableList(tmp);
		}
		else {
			this.children = Collections.emptyList();
		}
	}

	public String getName() {
		return tagName;
	}

	public Attributes getAttributes() {
		return attributes;
	}

	public Collection<Tag> getChildren() {
		return this.children;
	}
}
