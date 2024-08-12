package webx;

/**
 *
 * @author hexaredecimal
 */
public class Dt extends GenZContainerElement {
	@Override
	public String render() {
		return String.format("<dt %s>\n%s</div>", attributes.render(), super.render());
	}
}
