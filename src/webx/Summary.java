package webx;

/**
 *
 * @author hexaredecimal
 */
public class Summary extends WebXContainerElement {
	protected String innerText; 

	public Summary() {
		this.innerText = ""; 
	}
	
	public Summary(String text) {
		this.innerText = text; 
	}
	
	@Override
	public String render() {
		return String.format("<summary %s>%s\n%s</summary>", attributes.render(), innerText, super.render());

	}
}
