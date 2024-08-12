package webx;

/**
 *
 * @author hexaredecimal
 */
public class Mark extends GenZContainerElement {
	protected String innerText; 

	public Mark() {
		this.innerText = ""; 
	}
	
	public Mark(String text) {
		this.innerText = text; 
	}
	
	@Override
	public String render() {
		return String.format("<mark %s>%s\n%s</mark>", attributes.render(), innerText, super.render());
	}
}
