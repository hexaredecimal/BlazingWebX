package webx;

/**
 *
 * @author hexaredecimal
 */
public class Li extends WebXContainerElement {
	protected String innerText; 

	public Li() {
		this.innerText = ""; 
	}
	
	public Li(String text) {
		this.innerText = text; 
	}
	
	@Override
	public String render() {
		return String.format("<li %s>%s\n%s</li>", attributes.render(), innerText, super.render());
	}
}
