package genz;

/**
 *
 * @author hexaredecimal
 */
public class Track extends GenZElement {
	
	@Override
	public String render() {
		return String.format("<track %s />", this.attributes.render());
	}
}
