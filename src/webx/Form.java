package webx;

/**
 *
 * @author hexaredecimal
 */
public class Form extends GenZContainerElement {
	@Override
	public String render() {
		return String.format("<form %s>\n%s</form>", attributes.render(), super.render());
	}
}
