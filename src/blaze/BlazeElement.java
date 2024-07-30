package blaze;

/**
 *
 * @author hexaredecimal
 */
public abstract class BlazeElement {
	protected BlazeAttributes attributes = new BlazeAttributes(); 

	public BlazeElement addAttribute(String key, String value) {
		this.attributes.addAttribute(key, value);
		return this; 
	}

	public BlazeElement hxPost(String path) {
		this.addAttribute("hx-post", path);
		return this;
	}

	public BlazeElement hxGet(String path) {
		this.addAttribute("hx-get", path);
		return this;
	}

	public BlazeElement id(String id) {
		this.addAttribute("id", id);
		return this;
	}
	
	public BlazeElement className(String names) {
		this.addAttribute("class", names);
		return this;
	}
	public abstract String render(); 
}
