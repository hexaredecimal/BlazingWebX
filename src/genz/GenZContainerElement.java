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

	public GenZContainerElement addChildren(GenZElement... children) {
		for (GenZElement child: children) {
			this.children.add(child); 
		}
		return this; 
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
	public GenZContainerElement hxVals(String val) {
		super.hxVals(val);
		return this;
	}

	@Override
	public GenZContainerElement hxVars(String val) {
		super.hxVars(val);
		return this;
	}

	@Override
	public GenZContainerElement hxPost(String path) {
		super.hxPost(path);
		return this;
	}

	@Override
	public GenZContainerElement hxGet(String path) {
		super.hxGet(path);
		return this;
	}

	@Override
	public GenZContainerElement hxSwap(String target) {
		super.hxSwap(target);
		return this;
	}

	@Override
	public GenZContainerElement id(String id) {
		super.id(id);
		return this;
	}
	
	public GenZContainerElement hxTarget(String target) {
		super.hxTarget(target);
		return this;
	}

	public GenZContainerElement hxTrigger(String trigger) {
		super.hxTrigger(trigger);
		return this;
	}

	@Override
	public GenZContainerElement className(String names) {
		super.className(names);
		return this;
	}

}
