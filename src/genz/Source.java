package genz;

/**
 *
 * @author hexaredecimal
 */
public class Source extends GenZElement {
	
	@Override
	public String render() {
		return String.format("<source %s />", this.attributes.render());
	}
}
