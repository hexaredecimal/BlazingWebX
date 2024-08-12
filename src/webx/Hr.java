package webx;

/**
 *
 * @author hexaredecimal
 */
public class Hr extends WebXElement {

	@Override
	public String render() {
		return String.format("<hr %s>", attributes.render());
	}
}
