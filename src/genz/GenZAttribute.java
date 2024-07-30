package genz;

/**
 *
 * @author hexaredecimal
 */
public class GenZAttribute {
	private String key, value; 
	
	public GenZAttribute(String key, String value)  {
		this.key = key;
		this.value = value; 
	}

	public String render() {
		return String.format("%s=\"%s\"", key, value);
	}
}
