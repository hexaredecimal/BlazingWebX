package webx;

/**
 *
 * @author hexaredecimal
 */
public class Output extends GenZContainerElement {
	@Override
	public String render() {
		return String.format("<output %s>\n%s</output>", attributes.render(), super.render());
	}
}
