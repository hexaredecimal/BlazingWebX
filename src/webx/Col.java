package webx;

/**
 *
 * @author hexaredecimal
 */
public class Col extends WebXElement {

	
	@Override
	public String render() {
		return String.format("<col %s />", this.attributes.render());
	}
}
