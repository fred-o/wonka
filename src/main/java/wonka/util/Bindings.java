package wonka.util;

import java.util.*;
import java.util.Iterator;

/**
 * Simple immutable linked list implementation.
 */
public class Bindings<N, V> implements Iterable<Pair<N, V>> {
	private final N name;
	private final V value;
	private final Bindings<N, V> next;

	public Bindings(N name, V value) {
		this(name, value, null);
	}
	
	public Bindings(N name, V value, Bindings<N, V> next) {
		this.name = name;
		this.value = value;
		this.next = next;
	}

	public Bindings set(N name, V value) {
		return new Bindings(name, value, this);
	}

	public V get(N name) {
		if (this.name.equals(name)) {
			return this.value;
		}
		if (this.next == null) {
			return null;
		}
		return next.get(name);
	}

	public Iterator<Pair<N, V>> iterator() {
		List<Pair<N, V>> list = new LinkedList<Pair<N, V>>();
		Set<N> names = new HashSet<N>();
		for(Bindings<N, V> b = this; b != null; b = b.next) {
			if (!names.contains(b.name)) {
				list.add(0, new Pair<N, V>(b.name, b.value));
			}
			names.add(b.name);
		}
		return list.iterator();
	}
}