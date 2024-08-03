package genz;

/**
 *
 * @author hexaredecimal
 */
public class Figure extends GenZContainerElement {
	@Override
	public String render() {
		return String.format("<figure %s>\n%s</figure>", attributes.render(), super.render());
	}
}
