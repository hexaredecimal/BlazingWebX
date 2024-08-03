package genz;

/**
 *
 * @author hexaredecimal
 */
public class Header extends GenZContainerElement {
	@Override
	public String render() {
		return String.format("<header %s>\n%s</header>", attributes.render(), super.render());
	}
}
