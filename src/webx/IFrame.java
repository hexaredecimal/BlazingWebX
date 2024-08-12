package webx;

/**
 *
 * @author hexaredecimal
 */
public class IFrame extends WebXContainerElement {
	@Override
	public String render() {
		return String.format("<iframe %s>\n%s</iframe>", attributes.render(), super.render());
	}
}
