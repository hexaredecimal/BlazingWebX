package webx;

/**
 *
 * @author hexaredecimal
 */
public class Scarfold extends WebXContainerElement {
	String contents;

	public Scarfold(String contents) {
		this.contents = contents;
	}
	
	@Override
	public String render() {
		return String.format("%s %s", contents, super.render());
	}
}
