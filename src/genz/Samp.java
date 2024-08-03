package genz;

/**
 *
 * @author hexaredecimal
 */
public class Samp extends GenZContainerElement {
	protected String innerText; 

	public Samp() {
		this.innerText = ""; 
	}
	
	public Samp(String text) {
		this.innerText = text; 
	}
	
	@Override
	public String render() {
		return String.format("<samp %s>%s\n%s</samp>", attributes.render(), innerText, super.render());
	}
}
