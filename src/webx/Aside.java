package webx;

/**
 *
 * @author hexaredecimal
 */
public class Aside extends WebXContainerElement {
	@Override
	public String render() {
		return String.format("<aside %s>\n%s</aside>", attributes.render(), super.render());
	}
}
