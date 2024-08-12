package webx;

/**
 *
 * @author hexaredecimal
 */
public class Canvas extends WebXContainerElement {
	@Override
	public String render() {
		return String.format("<canvas %s>\n%s</canvas>", attributes.render(), super.render());
	}
}
