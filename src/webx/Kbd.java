package webx;

/**
 *
 * @author hexaredecimal
 */
public class Kbd extends GenZContainerElement {
	protected String innerText; 

	public Kbd() {
		this.innerText = ""; 
	}
	
	public Kbd(String text) {
		this.innerText = text; 
	}
	
	@Override
	public String render() {
		return String.format("<kbd %s>%s\n%s</kbd>", attributes.render(), innerText, super.render());
	}
}
