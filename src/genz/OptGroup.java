package genz;

/**
 *
 * @author hexaredecimal
 */
public class OptGroup extends GenZContainerElement {
	@Override
	public String render() {
		return String.format("<optgroup %s>\n%s</optgroup>", attributes.render(), super.render());
	}
}
