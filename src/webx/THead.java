package webx;

/**
 *
 * @author hexaredecimal
 */
public class THead extends WebXContainerElement {
	@Override
	public String render() {
		return String.format("<thead %s>\n%s</thead>", attributes.render(), super.render());
	}
}
