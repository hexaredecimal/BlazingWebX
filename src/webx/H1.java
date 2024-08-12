package webx;

/**
 *
 * @author hexaredecimal
 */
public class H1 extends WebXContainerElement {
	protected String innerText; 

	public H1() {
		this.innerText = ""; 
	}
	
	public H1(String text) {
		this.innerText = text; 
	}
	
	
	@Override
	public String render() {
		return String.format("<h1 %s>%s\n%s</h1>", attributes.render(), innerText, super.render());
	}
}
