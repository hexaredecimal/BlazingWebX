package webx;

/**
 *
 * @author hexaredecimal
 */
public class Path extends WebXElement {
	@Override
	public String render() {
		
		return String.format("<path %s />", this.attributes.render());
	}
}
