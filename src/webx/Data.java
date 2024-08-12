package webx;

/**
 *
 * @author hexaredecimal
 */
public class Data extends GenZContainerElement {
	@Override
	public String render() {
		return String.format("<data %s>\n%s</data>", attributes.render(), super.render());
	}
}
