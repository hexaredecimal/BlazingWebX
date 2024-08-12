package webx;

/**
 *
 * @author hexaredecimal
 */
public class Details extends WebXContainerElement {
	@Override
	public String render() {
		return String.format("<details %s>\n%s</details>", attributes.render(), super.render());
	}
}
