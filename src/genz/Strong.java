package genz;

/**
 *
 * @author hexaredecimal
 */
public class Strong extends GenZContainerElement {
	protected String innerText; 

	public Strong() {
		this.innerText = ""; 
	}
	
	public Strong(String text) {
		this.innerText = text; 
	}
	
	@Override
	public String render() {
		return String.format("<strong %s>%s\n%s</strong>", attributes.render(), innerText, super.render());

	}
}
