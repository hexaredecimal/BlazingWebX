package webx;

/**
 *
 * @author hexaredecimal
 */
public class Nav extends WebXContainerElement {
	@Override
	public String render() {
		return String.format("<nav %s>\n%s</nav>", attributes.render(), super.render());
	}
}
