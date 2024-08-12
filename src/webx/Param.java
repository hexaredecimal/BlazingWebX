package webx;

/**
 *
 * @author hexaredecimal
 */
public class Param extends WebXElement {
	@Override
	public String render() {
		return String.format("<param %s />", this.attributes.render());
	}
}
