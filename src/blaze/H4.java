package blaze;

/**
 *
 * @author hexaredecimal
 */
public class H4 extends BlazeContainerElement {
	protected String innerText; 

	public H4() {
		this.innerText = ""; 
	}
	
	public H4(String text) {
		this.innerText = text; 
	}
	
	
	@Override
	public String render() {
		return String.format("<h4 %s>%s\n%s</h4>", attributes.render(), innerText, super.render());
	}
}
