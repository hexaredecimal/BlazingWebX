package genz;

/**
 *
 * @author hexaredecimal
 */
public class Area extends GenZElement {

	public Area() {
	}
	
	@Override
	public String render() {
		
		return String.format("<area %s />", this.attributes.render());
	}
}
