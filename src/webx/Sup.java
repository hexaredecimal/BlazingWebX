package webx;

/**
 *
 * @author hexaredecimal
 */
public class Sup extends GenZContainerElement {
	protected String innerText; 

	public Sup() {
		this.innerText = ""; 
	}
	
	public Sup(String text) {
		this.innerText = text; 
	}
	
	@Override
	public String render() {
		return String.format("<sup %s>%s\n%s</sup>", attributes.render(), innerText, super.render());

	}
}
