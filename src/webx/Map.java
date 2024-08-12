package webx;

/**
 *
 * @author hexaredecimal
 */
public class Map extends GenZContainerElement {
	@Override
	public String render() {
		return String.format("<map %s>\n%s</map>", attributes.render(), super.render());
	}
}
