package webx;

/**
 *
 * @author hexaredecimal
 */
public class Canvas extends GenZContainerElement {
	@Override
	public String render() {
		return String.format("<canvas %s>\n%s</canvas>", attributes.render(), super.render());
	}
}
