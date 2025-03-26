package blazing.https;

import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 *
 * @author hexaredecimal
 */
public class BlazingSession {
	private HashMap map;
	private static BlazingSession the = null;
	private BlazingSession() {
		map = new HashMap<>();
	}

	public static BlazingSession getSession() {
		if (the == null) {
			the = new BlazingSession();
		}
		return the;
	}
	
	public void setAttribute(Object key, Object value) {
		this.map.put(key, value);
	}

	public Object getAttribute(Object key) {
		return this.map.get(key);
	}

	public boolean hasKey(Object key) {
		return this.map.containsKey(key);
	}

	public boolean hasValue(Object key) {
		return this.map.containsKey(key);
	}

	public List<SimpleEntry> getAttributes() {
		var list = new ArrayList<SimpleEntry>();
		var m = (Set<HashMap.Entry<?,?>>) this.map.entrySet();
		for (var kv : m) {
			var e = new SimpleEntry<?, ?>(kv.getKey(), kv.getValue());
			list.add(e);
		}
		return list;
	}
}
