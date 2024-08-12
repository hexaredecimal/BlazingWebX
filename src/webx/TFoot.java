package webx;

/**
 *
 * @author hexaredecimal
 */
public class TFoot extends GenZContainerElement {
	@Override
	public String render() {
		return String.format("<tfoot %s>\n%s</tfoot>", attributes.render(), super.render());
	}
}
