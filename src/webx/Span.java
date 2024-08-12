package webx;

/**
 *
 * @author hexaredecimal
 */
public class Span extends GenZContainerElement {
	@Override
	public String render() {
		return String.format("<span %s>\n%s</span>", attributes.render(), super.render());
	}
}
