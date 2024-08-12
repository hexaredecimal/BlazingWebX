package webx;

/**
 *
 * @author hexaredecimal
 */
public class Dl extends GenZContainerElement {
	@Override
	public String render() {
		return String.format("<dl %s>\n%s</div>", attributes.render(), super.render());
	}
}
