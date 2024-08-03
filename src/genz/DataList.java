package genz;

/**
 *
 * @author hexaredecimal
 */
public class DataList extends GenZContainerElement {
	@Override
	public String render() {
		return String.format("<datalist %s>\n%s</datalist>", attributes.render(), super.render());
	}
}
