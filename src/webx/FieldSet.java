package webx;

/**
 *
 * @author hexaredecimal
 */
public class FieldSet extends WebXContainerElement {
	@Override
	public String render() {
		return String.format("<fieldset %s>\n%s</fieldset>", attributes.render(), super.render());
	}
}
