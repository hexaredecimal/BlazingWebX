package webx;

/**
 *
 * @author hexaredecimal
 */
public class Tr extends WebXContainerElement {
	@Override
	public String render() {
		return String.format("<tr %s>\n%s</tr>", attributes.render(), super.render());
	}
}
