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

	public GenZElement hxVals(String val) {
		this.attr("hx-vals", val);
		return this;
	}

	public GenZElement hxVars(String val) {
		this.attr("hx-vars", val);
		return this;
	}

	public GenZElement hxTrigger(String trigger) {
		this.attr("hx-trigger", trigger);
		return this;
	}

	public GenZElement hxTarget(String target) {
		this.attr("hx-target", target);
		return this;
	}

	public GenZElement hxSwap(String target) {
		this.attr("hx-swap", target);
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
