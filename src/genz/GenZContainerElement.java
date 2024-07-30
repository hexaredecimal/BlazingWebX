package genz;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author hexaredecimal
 */
public class GenZContainerElement extends GenZElement {
	protected List<GenZElement> children; 
	
	public GenZContainerElement() {
		this.children = new ArrayList<>();
	}

	public GenZContainerElement addChild(GenZElement child) {
		this.children.add(child); 
		return this; 
	}

	@Override
	public GenZContainerElement addAttribute(String key, String value) {
		super.addAttribute(key, value);
		return this; 
	}
	
	@Override
	public String render() {
		final StringBuilder sb = new StringBuilder(); 
		children
			.forEach(child -> {
				sb.append(child.render().indent(4));
			});

		return sb.toString(); 
	}

	@Override
	public GenZContainerElement hxPost(String path) {
		super.addAttribute("hx-post", path);
		return this;
	}

	@Override
	public GenZContainerElement hxGet(String path) {
		super.addAttribute("hx-get", path);
		return this;
	}

	@Override
	public GenZContainerElement id(String id) {
		super.addAttribute("id", id);
		return this;
	}
	
	@Override
	public GenZContainerElement className(String names) {
		super.addAttribute("class", names);
		return this;
	}

}
