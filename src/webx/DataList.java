package webx;

/**
 *
 * @author hexaredecimal
 */
public class DataList extends WebXContainerElement {
	@Override
	public String render() {
		return String.format("<datalist %s>\n%s</datalist>", attributes.render(), super.render());
	}
}
