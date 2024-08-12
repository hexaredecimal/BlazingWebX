package webx;

/**
 *
 * @author hexaredecimal
 */
public class Article extends WebXContainerElement {
	@Override
	public String render() {
		return String.format("<article %s>\n%s</article>", attributes.render(), super.render());
	}
}
