package wonka.html.tag;

import wonka.html.render.*;
import java.util.*;

/**
 * This tag represents a raw text chunk.
 * 
 * @author fredrik
 */
public class Text implements Tag, Renderable {
	public final String text;

	public Text(String text) {
		this.text = text;
	}

	public String getName() {
		return "";
	}

	public Attributes getAttributes() {
		return null;
	}

	public Collection<Tag> getChildren() {
		return Collections.emptyList();
	}

	public boolean isOpen() {
		return false;
	}

	public byte[] render() {
		return text.getBytes();
	}

}
