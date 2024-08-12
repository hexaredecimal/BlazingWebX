package webx;

/**
 *
 * @author hexaredecimal
 */
public class Section extends WebXContainerElement {
	@Override
	public String render() {
		return String.format("<section %s>\n%s</section>", attributes.render(), super.render());
	}
}
