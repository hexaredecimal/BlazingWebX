package webx;

/**
 *
 * @author hexaredecimal
 */
public class Caption extends WebXContainerElement {
	protected String innerText; 

	public Caption() {
		this.innerText = ""; 
	}
	
	public Caption(String text) {
		this.innerText = text; 
	}
	
	
	@Override
	public String render() {
		return String.format("<caption %s>%s\n%s</caption>", attributes.render(), innerText, super.render());
	}
}
