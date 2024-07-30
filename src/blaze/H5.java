package blaze;

/**
 *
 * @author hexaredecimal
 */
public class H5 extends BlazeContainerElement {
	protected String innerText; 

	public H5() {
		this.innerText = ""; 
	}
	
	public H5(String text) {
		this.innerText = text; 
	}
	
	
	@Override
	public String render() {
		return String.format("<h5 %s>%s\n%s</h5>", attributes.render(), innerText, super.render());
	}
}
