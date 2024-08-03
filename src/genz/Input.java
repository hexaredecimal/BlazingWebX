package genz;

/**
 *
 * @author hexaredecimal
 */
public class Input extends GenZElement {

	@Override
	public String render() {
		return String.format("<input %s />", this.attributes.render());
	}
}
