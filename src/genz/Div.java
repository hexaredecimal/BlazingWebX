package genz;

/**
 *
 * @author hexaredecimal
 */
public class Div extends GenZContainerElement {
	@Override
	public String render() {
		return String.format("<div %s>\n%s</div>", attributes.render(), super.render());
	}
}
