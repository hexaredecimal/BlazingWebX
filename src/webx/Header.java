package webx;

/**
 *
 * @author hexaredecimal
 */
public class Header extends WebXContainerElement {
	@Override
	public String render() {
		return String.format("<header %s>\n%s</header>", attributes.render(), super.render());
	}
}
