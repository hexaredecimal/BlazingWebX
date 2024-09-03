package webx;

/**
 *
 * @author hexaredecimal
 */
public class Meta extends WebXElement {
	public Meta() {
	}
	
	
	@Override
	public String render() {
		return String.format("<meta %s />", this.attributes.render());
	}
}
