package webx;

/**
 *
 * @author hexaredecimal
 */
public class Em extends GenZContainerElement {
	protected String innerText; 

	public Em() {
		this.innerText = ""; 
	}
	
	public Em(String text) {
		this.innerText = text; 
	}
	
	
	@Override
	public String render() {
		return String.format("<em %s>%s\n%s</em>", attributes.render(), innerText, super.render());

	}
}
