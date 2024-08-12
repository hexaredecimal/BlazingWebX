package webx;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author hexaredecimal
 */
public class WebXContainerElement extends WebXElement {
	protected List<WebXElement> children; 
	
	public WebXContainerElement() {
		this.children = new ArrayList<>();
	}

	public WebXContainerElement addChildren(WebXElement... children) {
		for (WebXElement child: children) {
			this.children.add(child); 
		}
		return this; 
	}

	public WebXContainerElement addChild(WebXElement child) {
		this.children.add(child); 
		return this; 
	}

	@Override
	public WebXContainerElement attr(String key, String value) {
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
	public WebXContainerElement hxVals(String val) {
		super.hxVals(val);
		return this;
	}

	@Override
	public WebXContainerElement hxPost(String path) {
		super.hxPost(path);
		return this;
	}

	@Override
	public WebXContainerElement hxGet(String path) {
		super.hxGet(path);
		return this;
	}

	@Override
	public WebXContainerElement hxSwap(String target) {
		super.hxSwap(target);
		return this;
	}

	@Override
	public WebXContainerElement id(String id) {
		super.id(id);
		return this;
	}
	
	public WebXContainerElement hxTarget(String target) {
		super.hxTarget(target);
		return this;
	}

	public WebXContainerElement hxTrigger(String trigger) {
		super.hxTrigger(trigger);
		return this;
	}

	@Override
	public WebXContainerElement className(String names) {
		super.className(names);
		return this;
	}

}
