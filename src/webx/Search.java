package webx;

/**
 *
 * @author hexaredecimal
 */
public class Search extends WebXContainerElement {
	@Override
	public String render() {
		return String.format("<search %s>\n%s</search>", attributes.render(), super.render());
	}
}
