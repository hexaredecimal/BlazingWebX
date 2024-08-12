package webx;

/**
 *
 * @author hexaredecimal
 */
public class H3 extends WebXContainerElement {
	protected String innerText; 

	public H3() {
		this.innerText = ""; 
	}
	
	public H3(String text) {
		this.innerText = text; 
	}
	
	
	@Override
	public String render() {
		return String.format("<h3 %s>%s\n%s</h3>", attributes.render(), innerText, super.render());
	}
}
