package genz;

/**
 *
 * @author hexaredecimal
 */
public class TBody extends GenZContainerElement {
	@Override
	public String render() {
		return String.format("<tbody %s>\n%s</tbody>", attributes.render(), super.render());
	}
}
