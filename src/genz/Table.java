package genz;

/**
 *
 * @author hexaredecimal
 */
public class Table extends GenZContainerElement {
	@Override
	public String render() {
		return String.format("<table %s>\n%s</table>", attributes.render(), super.render());
	}
}
