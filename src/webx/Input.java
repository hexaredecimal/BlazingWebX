package webx;

/**
 *
 * @author hexaredecimal
 */
public class Input extends WebXElement {

	@Override
	public String render() {
		return String.format("<input %s />", this.attributes.render());
	}
}
