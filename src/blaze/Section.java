package blaze;

/**
 *
 * @author hexaredecimal
 */
public class Section extends BlazeContainerElement {
	@Override
	public String render() {
		return String.format("<section %s>\n%s</section>", attributes.render(), super.render());
	}
}
