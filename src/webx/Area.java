package webx;

/**
 *
 * @author hexaredecimal
 */
public class Area extends WebXElement {

	public Area() {
	}
	
	@Override
	public String render() {
		
		return String.format("<area %s />", this.attributes.render());
	}
}
