package genz;

/**
 *
 * @author hexaredecimal
 */
public class Ul extends GenZContainerElement {
	@Override
	public String render() {
		return String.format("<ul %s>\n%s</ul>", attributes.render(), super.render());
	}
}
