package blazing.json;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author ERC
 */
public class JSon {

	public static String from(Map<?, ?> map) {
		StringBuilder sb = new StringBuilder();
		sb.append("{".indent(0));
		var entries = map.entrySet().toArray();
		for (int i = 0; i < entries.length; i++) {
			var entry = (Map.Entry) entries[i];
			Object key = entry.getKey();
			Object value = entry.getValue();
			String end = i < entries.length - 1 ? "," : "";
			String line = String.format("\"%s\": \"%s\"%s", key, value, end);

			if (value instanceof Double || value instanceof Integer) {
				line = String.format("\"%s\": %s%s", key, value, end);
			} else if (value instanceof Map<?, ?> child) {
				value = JSon.from(child).trim();
				line = String.format("\"%s\": %s%s", key, value, end);
			}
			sb.append(line.indent(4));
		}
		sb.append("}".indent(0));
		return sb.toString();
	}
}
