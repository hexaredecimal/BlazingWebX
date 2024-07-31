package genz;

/**
 *
 * @author hexaredecimal
 */
public abstract class GenZElement {
	protected GenZAttributes attributes = new GenZAttributes(); 

	public GenZElement attr(String key, String value) {
		this.attributes.addAttribute(key, value);
		return this; 
	}

	public GenZElement hxPost(String path) {
		this.attr("hx-post", path);
		return this;
	}

	public GenZElement hxGet(String path) {
		this.attr("hx-get", path);
		return this;
	}

	public GenZElement id(String id) {
		this.attr("id", id);
		return this;
	}
	
	public GenZElement className(String names) {
		this.attr("class", names);
		return this;
	}
	public abstract String render(); 
}
