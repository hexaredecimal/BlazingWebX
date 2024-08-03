package genz;

/**
 *
 * @author hexaredecimal
 */
public class Picture extends GenZContainerElement {
	@Override
	public String render() {
		return String.format("<picture %s>\n%s</picture>", attributes.render(), super.render());
	}
}
