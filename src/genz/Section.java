package genz;

/**
 *
 * @author hexaredecimal
 */
public class Section extends GenZContainerElement {
	@Override
	public String render() {
		return String.format("<section %s>\n%s</section>", attributes.render(), super.render());
	}
}