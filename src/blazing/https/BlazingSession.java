package blazing.https;

import blazing.types.Result;
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
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

	/**
	 * Gets the current web session, creates a new one if none exists.
	 * @return The session object
	*/
	public static BlazingSession getSession() {
		if (the == null) {
			the = new BlazingSession();
		}
		return the;
	}
	
	/**
	 * Sets a value of an attribute
	 * @param key - The key of the attribute
	 * @param value - The value to assign
	 */
	public void setAttribute(Object key, Object value) {
		this.map.put(key, value);
	}

	/**
	 * Gets the value stored at key. 
	 * @param key
	 * @return - A result object with the value stored
	 */
	public Result<Object, String> getAttribute(Object key) {
		var value = this.map.get(key);
		return value == null
			? Result.err("Key `" + key + "` is not in session") : Result.ok(value);
	}

	public boolean hasKey(Object key) {
		return this.map.containsKey(key);
	}

	public boolean hasValue(Object key) {
		return this.map.containsKey(key);
	}

	public List<SimpleEntry> getAttributes() {
		var list = new ArrayList<SimpleEntry>();
		var m = (Set<HashMap.Entry>) this.map.entrySet();
		for (var kv : m) {
			var e = new SimpleEntry(kv.getKey(), kv.getValue());
			list.add(e);
		}
		return list;
	}

	public void invalidate() {
		map.clear();
		the = null;
	}
}
