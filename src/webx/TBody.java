package webx;

/**
 *
 * @author hexaredecimal
 */
public class TBody extends WebXContainerElement {
	@Override
	public String render() {
		return String.format("<tbody %s>\n%s</tbody>", attributes.render(), super.render());
	}
}
