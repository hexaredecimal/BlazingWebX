package blazing.json;

import java.lang.reflect.Array;
import java.util.Arrays;
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
		final String STANDARD_FMT = "\"%s\": %s%s";
		var entries = map.entrySet().toArray();
		for (int i = 0; i < entries.length; i++) {
			var entry = (Map.Entry) entries[i];
			Object key = entry.getKey();
			Object value = entry.getValue();
			String end = i < entries.length - 1 ? "," : "";
			String line = String.format("\"%s\": \"%s\"%s", key, value, end);

			if (value.getClass().isArray()) {
				int length = Array.getLength(value);
				Object[] boxedArray = new Object[length];
				for (int j = 0; j < length; j++) {
					var object = Array.get(value, j);
          if (object instanceof Map hashmap) {
            if (map == hashmap) { 
              continue;
            }
            boxedArray[j] = String.format("\n%s", from(hashmap)).indent(4);
          } else {
            boxedArray[j] = object;
          }
				}
				line = String.format(STANDARD_FMT, key, Arrays.toString(boxedArray), end);
			} else if (value instanceof Double
				|| value instanceof Integer
				|| value instanceof Long
				|| value instanceof Short
				|| value instanceof Byte) {
				line = String.format(STANDARD_FMT, key, value, end);
			} else if (value instanceof Map<?, ?> child) {
				value = JSon.from(child).trim();
				line = String.format(STANDARD_FMT, key, value, end);
			}
			sb.append(line.indent(4));
		}
		sb.append("}".indent(0));
		return sb.toString();
	}
}
