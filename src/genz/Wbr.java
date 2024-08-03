package genz;

/**
 *
 * @author hexaredecimal
 */
public class Wbr extends GenZContainerElement {
	protected String innerText; 

	public Wbr() {
		this.innerText = ""; 
	}
	
	public Wbr(String text) {
		this.innerText = text; 
	}
	
	@Override
	public String render() {
		return String.format("<wbr %s>%s\n%s</wbr>", attributes.render(), innerText, super.render());
	}
}