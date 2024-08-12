package webx;

/**
 *
 * @author hexaredecimal
 */
public class Abbr extends WebXContainerElement {
	protected String innerText; 

	public Abbr() {
		this.innerText = ""; 
	}
	
	public Abbr(String text) {
		this.innerText = text; 
	}
	
	@Override
	public String render() {
		return String.format("<abbr %s>%s\n%s</abbr>", attributes.render(), innerText, super.render());

	}
}
