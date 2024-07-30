package blaze;

/**
 *
 * @author hexaredecimal
 */
public class Div extends BlazeContainerElement {
	@Override
	public String render() {
		return String.format("<div %s>\n%s</div>", attributes.render(), super.render());
	}
}
