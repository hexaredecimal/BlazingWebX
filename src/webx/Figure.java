package webx;

/**
 *
 * @author hexaredecimal
 */
public class Figure extends WebXContainerElement {
	@Override
	public String render() {
		return String.format("<figure %s>\n%s</figure>", attributes.render(), super.render());
	}
}
