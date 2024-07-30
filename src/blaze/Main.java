package blaze;

/**
 *
 * @author hexaredecimal
 */
public class Main extends BlazeContainerElement {
	@Override
	public String render() {
		return String.format("<main %s>\n%s</main>", attributes.render(), super.render());
	}
}
