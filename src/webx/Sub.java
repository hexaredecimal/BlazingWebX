package webx;

/**
 *
 * @author hexaredecimal
 */
public class Sub extends WebXContainerElement {
	protected String innerText; 

	public Sub() {
		this.innerText = ""; 
	}
	
	public Sub(String text) {
		this.innerText = text; 
	}
	
	@Override
	public String render() {
		return String.format("<sub %s>%s\n%s</sub>", attributes.render(), innerText, super.render());

	}
}
