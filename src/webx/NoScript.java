package webx;

/**
 *
 * @author hexaredecimal
 */
public class NoScript extends WebXContainerElement {
	protected String innerText; 

	public NoScript() {
		this.innerText = ""; 
	}
	
	public NoScript(String text) {
		this.innerText = text; 
	}
	
	@Override
	public String render() {
		return String.format("<noscript %s>%s\n%s</noscript>", attributes.render(), innerText, super.render());
	}
}
