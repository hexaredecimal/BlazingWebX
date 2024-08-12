package webx;

/**
 *
 * @author hexaredecimal
 */
public class Svg extends WebXContainerElement {
	@Override
	public String render() {
		return String.format("<svg %s>\n%s</svg>", attributes.render(), super.render());
	}
}
