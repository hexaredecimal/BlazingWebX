package webx;

/**
 *
 * @author hexaredecimal
 */
public class S extends WebXContainerElement {
	protected String innerText; 

	public S() {
		this.innerText = ""; 
	}
	
	public S(String text) {
		this.innerText = text; 
	}
	
	@Override
	public String render() {
		return String.format("<s %s>%s\n%s</s>", attributes.render(), innerText, super.render());
	}
}
