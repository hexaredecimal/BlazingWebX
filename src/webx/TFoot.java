package webx;

/**
 *
 * @author hexaredecimal
 */
public class TFoot extends WebXContainerElement {
	@Override
	public String render() {
		return String.format("<tfoot %s>\n%s</tfoot>", attributes.render(), super.render());
	}
}
