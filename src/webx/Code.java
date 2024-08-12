package webx;

/**
 *
 * @author hexaredecimal
 */
public class Code extends GenZContainerElement {
	protected String innerText; 

	public Code() {
		this.innerText = ""; 
	}
	
	public Code(String text) {
		this.innerText = text; 
	}
	
	
	@Override
	public String render() {
		return String.format("<code %s>%s\n%s</code>", attributes.render(), innerText, super.render());
	}
}
