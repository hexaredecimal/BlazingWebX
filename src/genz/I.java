package genz;

/**
 *
 * @author hexaredecimal
 */
public class I extends GenZContainerElement {
	protected String innerText; 

	public I() {
		this.innerText = ""; 
	}
	
	public I(String text) {
		this.innerText = text; 
	}
	
	@Override
	public String render() {
		return String.format("<i %s>%s\n%s</i>", attributes.render(), innerText, super.render());

	}
}
