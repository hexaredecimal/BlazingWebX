package webx;

/**
 *
 * @author hexaredecimal
 */
public class U extends WebXContainerElement {
	protected String innerText; 

	public U() {
		this.innerText = ""; 
	}
	
	public U(String text) {
		this.innerText = text; 
	}
	
	@Override
	public String render() {
		return String.format("<u %s>%s\n%s</u>", attributes.render(), innerText, super.render());
	}
}
