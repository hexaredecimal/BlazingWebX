package blaze;

/**
 *
 * @author hexaredecimal
 */
public class Content extends BlazeContainerElement {
	@Override
	public String render() {
		return String.format("<content %s>\n%s</content>", attributes.render(), super.render());
	}
}
