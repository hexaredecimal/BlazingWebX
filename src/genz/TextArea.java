package genz;

/**
 *
 * @author hexaredecimal
 */
public class TextArea extends GenZContainerElement {
	@Override
	public String render() {
		return String.format("<textarea %s>\n%s</textarea>", attributes.render(), super.render());
	}
}