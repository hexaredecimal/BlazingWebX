package webx;

/**
 *
 * @author hexaredecimal
 */
public class OptGroup extends WebXContainerElement {
	@Override
	public String render() {
		return String.format("<optgroup %s>\n%s</optgroup>", attributes.render(), super.render());
	}
}
