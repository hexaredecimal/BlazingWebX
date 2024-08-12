package webx;

/**
 *
 * @author hexaredecimal
 */
public class Div extends WebXContainerElement {
	@Override
	public String render() {
		return String.format("<div %s>\n%s</div>", attributes.render(), super.render());
	}
}
