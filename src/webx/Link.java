package webx;

/**
 *
 * @author hexaredecimal
 */
public class Link extends WebXElement {
	public Link() {
	}
	
	
	@Override
	public String render() {
		return String.format("<link %s />", this.attributes.render());
	}
}
