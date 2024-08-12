package webx;

/**
 *
 * @author hexaredecimal
 */
public class Bdo extends GenZContainerElement {
	protected String innerText; 

	public Bdo() {
		this.innerText = ""; 
	}
	
	public Bdo(String text) {
		this.innerText = text; 
	}
	
	@Override
	public String render() {
		return String.format("<bdo %s>%s\n%s</bdo>", attributes.render(), innerText, super.render());
	}
}
