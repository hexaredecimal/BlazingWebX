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
	public GenZContainerElement attr(String key, String value) {
		super.attr(key, value);
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
		super.attr("hx-post", path);
		return this;
	}

	@Override
	public GenZContainerElement hxGet(String path) {
		super.attr("hx-get", path);
		return this;
	}

	@Override
	public GenZContainerElement id(String id) {
		super.attr("id", id);
		return this;
	}
	
	@Override
	public GenZContainerElement className(String names) {
		super.attr("class", names);
		return this;
	}

}
