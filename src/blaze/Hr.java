package blaze;

/**
 *
 * @author hexaredecimal
 */
public class Hr extends BlazeElement {

	@Override
	public String render() {
		return String.format("<hr %s>", attributes.render());
	}
}
