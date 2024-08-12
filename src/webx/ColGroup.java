package webx;

/**
 *
 * @author hexaredecimal
 */
public class ColGroup extends WebXContainerElement {
	@Override
	public String render() {
		return String.format("<colgroup %s>\n%s</colgroup>", attributes.render(), super.render());
	}
}
