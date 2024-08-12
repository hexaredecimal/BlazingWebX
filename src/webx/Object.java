package webx;

/**
 *
 * @author hexaredecimal
 */
public class Object extends WebXContainerElement {
	@Override
	public String render() {
		return String.format("<object %s>\n%s</object>", attributes.render(), super.render());
	}
}
