package webx;

/**
 *
 * @author hexaredecimal
 */
public class B extends GenZContainerElement {
	protected String innerText; 

	public B() {
		this.innerText = ""; 
	}
	
	public B(String text) {
		this.innerText = text; 
	}
	
	@Override
	public String render() {
		return String.format("<b %s>%s\n%s</b>", attributes.render(), innerText, super.render());

	}
}
