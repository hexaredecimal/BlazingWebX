package webx;

/**
 *
 * @author hexaredecimal
 */
public class Ul extends WebXContainerElement {
	@Override
	public String render() {
		return String.format("<ul %s>\n%s</ul>", attributes.render(), super.render());
	}
}
