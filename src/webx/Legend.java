package webx;

/**
 *
 * @author hexaredecimal
 */
public class Legend extends WebXContainerElement {
	protected String innerText; 

	public Legend() {
		this.innerText = ""; 
	}
	
	public Legend(String text) {
		this.innerText = text; 
	}
	
	@Override
	public String render() {
		return String.format("<legend %s>%s\n%s</legend>", attributes.render(), innerText, super.render());
	}
}
