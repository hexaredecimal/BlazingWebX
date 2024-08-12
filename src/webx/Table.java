package webx;

/**
 *
 * @author hexaredecimal
 */
public class Table extends WebXContainerElement {
	@Override
	public String render() {
		return String.format("<table %s>\n%s</table>", attributes.render(), super.render());
	}
}
