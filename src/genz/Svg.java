package genz;

/**
 *
 * @author hexaredecimal
 */
public class Svg extends GenZContainerElement {
	@Override
	public String render() {
		return String.format("<svg %s>\n%s</svg>", attributes.render(), super.render());
	}
}
