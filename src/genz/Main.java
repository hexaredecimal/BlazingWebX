package genz;

/**
 *
 * @author hexaredecimal
 */
public class Main extends GenZContainerElement {
	@Override
	public String render() {
		return String.format("<main %s>\n%s</main>", attributes.render(), super.render());
	}
}
