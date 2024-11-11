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

	/**
	 * Appends multiple child nodes to the parent at once. 
	 * 
	 * @param children Comma separated list of children or an array of child nodes
	 * @return A modified element 
	 */
	@Deprecated
	public WebXContainerElement addChildren(WebXElement... children) {
		for (WebXElement child: children) {
			this.children.add(child); 
		}
		return this; 
	}

	/**
	 * Appends multiple child nodes to the parent at once. 
	 * 
	 * @param children Comma separated list of children or an array of child nodes
	 * @return A modified element 
	 */
	
	public WebXContainerElement add(WebXElement... children) {
		for (WebXElement child: children) {
			this.children.add(child); 
		}
		return this; 
	}
	
	/**
	 * Appends a child to the parent class
	 * 
	 * @param child The actual child node 
	 * @return A modified element 
	 */
	public WebXContainerElement add(WebXElement child) {
		this.children.add(child); 
		return this; 
	}

	
	/**
	 * Appends a child to the parent class
	 * 
	 * @param child The actual child node 
	 * @return A modified element 
	 */
	@Deprecated
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
	public WebXContainerElement hxSwapOob(String value) {
		super.hxSwapOob(value);
		return this;
	}

	@Override
	public WebXContainerElement hxBoost(boolean activate) {
		super.hxBoost(activate);
		return this;
	}

	@Override
	public WebXContainerElement hxConfirm(String message) {
		super.hxConfirm(message);
		return this;
	}

	@Override
	public WebXContainerElement hxOn(String event) {
		super.hxOn(event);
		return this;
	}
	
	@Override
	public WebXContainerElement hxExt(String ext) {
		super.hxExt(ext);
		return this;
	}

	@Override
	public WebXContainerElement hxWs(String url) {
		super.hxWs(url);
		return this;
	}

	@Override
	public WebXContainerElement hxSend() {
		super.hxSend();
		return this;
	}

	@Override
	public WebXContainerElement hxEncoding(String type) {
		super.hxEncoding(type);
		return this;
	}

	@Override
	public WebXContainerElement hxEncoding() {
		super.hxEncoding();
		return this;
	}

	@Override
	public WebXContainerElement hxSwap(String strategy) {
		super.hxSwap(strategy);
		return this;
	}

	@Override
	public WebXContainerElement id(String id) {
		super.id(id);
		return this;
	}
	
	@Override
	public WebXContainerElement hxTarget(String target) {
		super.hxTarget(target);
		return this;
	}

	@Override
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
