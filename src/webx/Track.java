package webx;

/**
 *
 * @author hexaredecimal
 */
public class Track extends WebXElement {
	
	@Override
	public String render() {
		return String.format("<track %s />", this.attributes.render());
	}
}
