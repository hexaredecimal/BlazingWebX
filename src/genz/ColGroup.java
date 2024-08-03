package genz;

/**
 *
 * @author hexaredecimal
 */
public class ColGroup extends GenZContainerElement {
	@Override
	public String render() {
		return String.format("<colgroup %s>\n%s</colgroup>", attributes.render(), super.render());
	}
}
