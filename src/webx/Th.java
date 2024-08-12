package webx;

/**
 *
 * @author hexaredecimal
 */
public class Th extends WebXContainerElement {
	protected String innerText; 

	public Th() {
		this.innerText = ""; 
	}
	
	public Th(String text) {
		this.innerText = text; 
	}
	
	@Override
	public String render() {
		return String.format("<th %s>%s\n%s</th>", attributes.render(), innerText, super.render());
	}
}
