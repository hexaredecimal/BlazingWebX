package webx;

/**
 *
 * @author hexaredecimal
 */
public class Address extends WebXContainerElement {
	@Override
	public String render() {
		return String.format("<address %s>\n%s</address>", attributes.render(), super.render());
	}
}
