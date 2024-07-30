package genz;

/**
 *
 * @author hexaredecimal
 */
public abstract class GenZElement {
	protected GenZAttributes attributes = new GenZAttributes(); 

	public GenZElement addAttribute(String key, String value) {
		this.attributes.addAttribute(key, value);
		return this; 
	}

	public GenZElement hxPost(String path) {
		this.addAttribute("hx-post", path);
		return this;
	}

	public GenZElement hxGet(String path) {
		this.addAttribute("hx-get", path);
		return this;
	}

	public GenZElement id(String id) {
		this.addAttribute("id", id);
		return this;
	}
	
	public GenZElement className(String names) {
		this.addAttribute("class", names);
		return this;
	}
	public abstract String render(); 
}
