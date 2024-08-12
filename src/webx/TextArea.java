package webx;

/**
 *
 * @author hexaredecimal
 */
public class TextArea extends WebXContainerElement {
	@Override
	public String render() {
		return String.format("<textarea %s>\n%s</textarea>", attributes.render(), super.render());
	}
}
