package genz;

/**
 *
 * @author hexaredecimal
 */
public class FieldSet extends GenZContainerElement {
	@Override
	public String render() {
		return String.format("<fieldset %s>\n%s</fieldset>", attributes.render(), super.render());
	}
}
