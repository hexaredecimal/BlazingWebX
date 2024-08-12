package webx;

/**
 *
 * @author hexaredecimal
 */
public class Source extends WebXElement {
	
	@Override
	public String render() {
		return String.format("<source %s />", this.attributes.render());
	}
}
