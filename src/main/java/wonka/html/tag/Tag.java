package wonka.html.tag;

import java.util.Collection;

/**
 * This interface represents a single tag. Implementing classes are supposed
 * to be immutable.
 * 
 * @author fredrik
 */
public interface Tag {	
	public String getName();

	public Attributes getAttributes();

	public Collection<Tag> getChildren();

	public boolean isOpen();
}
