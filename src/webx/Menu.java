package webx;

/**
 *
 * @author hexaredecimal
 */
public class Menu extends WebXContainerElement {
	@Override
	public String render() {
		return String.format("<menu %s>\n%s</menu>", attributes.render(), super.render());
	}
}
