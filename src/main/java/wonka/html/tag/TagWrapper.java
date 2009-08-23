package wonka.html.tag;

import java.util.*;
import java.lang.reflect.*;

import org.apache.log4j.*;

import static wonka.html.tag.Tags.*;

/**
 * @see Tags
 */
public class TagWrapper implements Iterable<Tag> {
	private static final Logger LOG = Logger.getLogger(TagWrapper.class);
	private static final Class<?>[] TAG_CONSTRUCTOR_SIGNATURE = new Class[] { new Tag[0].getClass() };
    private List<Tag> elements = new LinkedList<Tag>();

	public TagWrapper and(Tag... tags) {
		for(Tag tag: tags) {
			elements.add(tag);
		}
		return this;
	}

	public TagWrapper and(Iterable<Tag> tags) {
		for(Iterator<Tag> it = tags.iterator(); it.hasNext();) {
			elements.add(it.next());
		}
		return this;
	}

	public TagWrapper and(String... strings) {
		for(String str: strings) {
			elements.add(text(str));
		}
		return this;
	}
	
	public TagWrapper in(Class<? extends Tag> clazz) {
		List<Tag> res = new LinkedList<Tag>();
		try {
			Constructor<? extends Tag> constructor = clazz.getConstructor(TAG_CONSTRUCTOR_SIGNATURE);
			System.out.println(constructor.getParameterTypes()[0]);
			for(Tag child: elements) {
				try {
					Tag tag = constructor.newInstance(new Object[] { new Tag[] { child }});
					res.add(tag);
				}
				catch (IllegalAccessException iae) {
					LOG.error("Could not instantiate tag (" + clazz.getName() + ")", iae);
				}
				catch (InstantiationException ie) {
					LOG.error("Could not instantiate tag (" + clazz.getName() + ")", ie);
				}
				catch (InvocationTargetException ite) {
					LOG.error("Could not instantiate tag (" + clazz.getName() + ")", ite);
				}
			}
		}
		catch (NoSuchMethodException nsme) {
			LOG.error("Could not find a suitable constructor for tag (" + clazz.getName() + ")", nsme);
		}
		this.elements = res;
		return this;
	}

	public Iterator<Tag> iterator() {
		return elements.iterator();
	}
}