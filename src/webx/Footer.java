package webx;

/**
 *
 * @author hexaredecimal
 */
public class Footer extends WebXContainerElement {
	@Override
	public String render() {
		return String.format("<footer %s>\n%s</footer>", attributes.render(), super.render());
	}
}
