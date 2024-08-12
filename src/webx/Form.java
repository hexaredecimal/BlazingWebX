package webx;

/**
 *
 * @author hexaredecimal
 */
public class Form extends WebXContainerElement {
	@Override
	public String render() {
		return String.format("<form %s>\n%s</form>", attributes.render(), super.render());
	}
}
