package webx;

/**
 * Represents a single HTML attribute
 *
 * <pre>
 * {@code
 * 	<p style="color: red;"></p>
 * }
 * </pre>
 *
 * Attributes are used to add special user data to html elements for processing
 *
 * @author hexaredecimal
 */
public class GenZAttribute {
	private final String key, value;

	/**
	 * Creates a new attribute
	 * 
	 * <pre>
	 * {@code
	 * 	<p style="color: red;"></p>
	 * }
	 * </pre>
	 * 
	 * style is a Key and the String value is the value in this case
	 *
	 * @param key Left side of the attribute, represents the data name
	 * @param value Right side of the attribute, represents the value for the data
	 */
	public GenZAttribute(String key, String value) {
		this.key = key;
		this.value = value;
	}

	/**
	 * Combines the key and value into a valid HTML attribute
	 * 
	 * @return A String representation of the attribute. 
	 */
	public String render() {
		return String.format("%s='%s'", key, value);
	}
}
