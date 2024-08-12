package webx;

/**
 *
 * @author hexaredecimal
 */
public class Param extends GenZElement {
	@Override
	public String render() {
		return String.format("<param %s />", this.attributes.render());
	}
}
