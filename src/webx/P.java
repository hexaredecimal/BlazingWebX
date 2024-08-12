package webx;

/**
 *
 * @author hexaredecimal
 */
public class P extends GenZContainerElement {
	protected String innerText; 

	public P() {
		this.innerText = ""; 
	}
	
	public P(String text) {
		this.innerText = text; 
	}
	
	
	@Override
	public String render() {
		return String.format("<p %s>%s\n%s</p>", attributes.render(), innerText, super.render());

	}
}
