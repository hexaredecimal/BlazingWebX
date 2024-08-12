package webx;

/**
 *
 * @author hexaredecimal
 */
public class Main extends WebXContainerElement {
	@Override
	public String render() {
		return String.format("<main %s>\n%s</main>", attributes.render(), super.render());
	}
}
