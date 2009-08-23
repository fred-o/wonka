package wonka.html.render;

import wonka.util.*;
import wonka.html.tag.*;

public class TagRenderer {
    private Tag rootTag;

	public TagRenderer(Tag rootTag) {
		this.rootTag = rootTag;
	}

	private void renderTag(Tag tag, StringBuilder b) {
		if (tag instanceof Renderable) {
			b.append(new String(((Renderable) tag).render()));
		}
		else {
			b.append("<").append(tag.getName());
			if(tag.getAttributes() != null) {
				for (Pair<String, String> attr: tag.getAttributes()) {
					b.append(" ").append(attr.name).append("=\"").append(attr.value).append("\"");
				}
			}
			if (tag.isOpen()) {
				b.append(">");
				for(Tag child: tag.getChildren()) {
					renderTag(child, b);
				}
				b.append("</").append(tag.getName()).append(">");
			}
			else {
				b.append(" />");
			}
		}
	}

	public byte[] render() {
		StringBuilder b = new StringBuilder();
		renderTag(rootTag, b);
		return b.toString().getBytes();
	}
}