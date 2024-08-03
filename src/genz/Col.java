package genz;

/**
 *
 * @author hexaredecimal
 */
public class Col extends GenZElement {

	
	@Override
	public String render() {
		return String.format("<col %s />", this.attributes.render());
	}
}
