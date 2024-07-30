package blaze;

/**
 *
 * @author hexaredecimal
 */
public class BlazeAttribute {
	private String key, value; 
	
	public BlazeAttribute(String key, String value)  {
		this.key = key;
		this.value = value; 
	}

	public String render() {
		return String.format("%s=\"%s\"", key, value);
	}
}
