package webx;

/**
 *
 * @author hexaredecimal
 */
public class Ol extends GenZContainerElement {
	@Override
	public String render() {
		return String.format("<ol %s>\n%s</ol>", attributes.render(), super.render());
	}
}
