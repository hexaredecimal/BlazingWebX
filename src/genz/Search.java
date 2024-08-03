package genz;

/**
 *
 * @author hexaredecimal
 */
public class Search extends GenZContainerElement {
	@Override
	public String render() {
		return String.format("<search %s>\n%s</search>", attributes.render(), super.render());
	}
}
