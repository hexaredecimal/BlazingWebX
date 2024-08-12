package webx;

/**
 *
 * @author hexaredecimal
 */
public class H6 extends WebXContainerElement {
	protected String innerText; 

	public H6() {
		this.innerText = ""; 
	}
	
	public H6(String text) {
		this.innerText = text; 
	}
	
	
	@Override
	public String render() {
		return String.format("<h6 %s>%s\n%s</h6>", attributes.render(), innerText, super.render());
	}
}
