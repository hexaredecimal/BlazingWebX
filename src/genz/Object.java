package genz;

/**
 *
 * @author hexaredecimal
 */
public class Object extends GenZContainerElement {
	@Override
	public String render() {
		return String.format("<object %s>\n%s</object>", attributes.render(), super.render());
	}
}
