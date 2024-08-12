package webx;

/**
 *
 * @author hexaredecimal
 */
public class Small extends GenZContainerElement {
	protected String innerText; 

	public Small() {
		this.innerText = ""; 
	}
	
	public Small(String text) {
		this.innerText = text; 
	}
	
	@Override
	public String render() {
		return String.format("<small %s>%s\n%s</small>", attributes.render(), innerText, super.render());

	}
}
