package genz;

/**
 *
 * @author hexaredecimal
 */
public class THead extends GenZContainerElement {
	@Override
	public String render() {
		return String.format("<thead %s>\n%s</thead>", attributes.render(), super.render());
	}
}