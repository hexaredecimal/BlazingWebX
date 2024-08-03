package genz;

/**
 *
 * @author hexaredecimal
 */
public class Select extends GenZContainerElement {
	@Override
	public String render() {
		return String.format("<select %s>\n%s</select>", attributes.render(), super.render());
	}
}
