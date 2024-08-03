package genz;

/**
 *
 * @author hexaredecimal
 */
public class Menu extends GenZContainerElement {
	@Override
	public String render() {
		return String.format("<menu %s>\n%s</menu>", attributes.render(), super.render());
	}
}
