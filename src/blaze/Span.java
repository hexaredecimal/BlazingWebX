package blaze;

/**
 *
 * @author hexaredecimal
 */
public class Span extends BlazeContainerElement {
	@Override
	public String render() {
		return String.format("<span %s>\n%s</span>", attributes.render(), super.render());
	}
}
