package webx;

/**
 *
 * @author hexaredecimal
 */
public class Nav extends GenZContainerElement {
	@Override
	public String render() {
		return String.format("<nav %s>\n%s</nav>", attributes.render(), super.render());
	}
}
