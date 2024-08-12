package webx;

/**
 *
 * @author hexaredecimal
 */
public class Strong extends WebXContainerElement {
	protected String innerText; 

	public Strong() {
		this.innerText = ""; 
	}
	
	public Strong(String text) {
		this.innerText = text; 
	}
	
	@Override
	public String render() {
		return String.format("<strong %s>%s\n%s</strong>", attributes.render(), innerText, super.render());

	}
}
