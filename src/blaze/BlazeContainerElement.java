package blaze;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author hexaredecimal
 */
public class BlazeContainerElement extends BlazeElement {
	protected List<BlazeElement> children; 
	
	public BlazeContainerElement() {
		this.children = new ArrayList<>();
	}

	public BlazeContainerElement addChild(BlazeElement child) {
		this.children.add(child); 
		return this; 
	}

	@Override
	public BlazeContainerElement addAttribute(String key, String value) {
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
	public BlazeContainerElement hxPost(String path) {
		super.addAttribute("hx-post", path);
		return this;
	}

	@Override
	public BlazeContainerElement hxGet(String path) {
		super.addAttribute("hx-get", path);
		return this;
	}

	@Override
	public BlazeContainerElement id(String id) {
		super.addAttribute("id", id);
		return this;
	}
	
	@Override
	public BlazeContainerElement className(String names) {
		super.addAttribute("class", names);
		return this;
	}

}
