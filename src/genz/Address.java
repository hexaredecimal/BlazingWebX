package genz;

/**
 *
 * @author hexaredecimal
 */
public class Address extends GenZContainerElement {
	@Override
	public String render() {
		return String.format("<address %s>\n%s</address>", attributes.render(), super.render());
	}
}
