package genz;

/**
 *
 * @author hexaredecimal
 */
public class Details extends GenZContainerElement {
	@Override
	public String render() {
		return String.format("<details %s>\n%s</details>", attributes.render(), super.render());
	}
}
