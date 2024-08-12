package webx;

/**
 *
 * @author hexaredecimal
 */
public class Ins extends GenZContainerElement {
	protected String innerText; 

	public Ins() {
		this.innerText = ""; 
	}
	
	public Ins(String text) {
		this.innerText = text; 
	}
	
	@Override
	public String render() {
		return String.format("<ins %s>%s\n%s</ins>", attributes.render(), innerText, super.render());
	}
}
