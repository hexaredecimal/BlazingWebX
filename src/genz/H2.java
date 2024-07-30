package genz;

/**
 *
 * @author hexaredecimal
 */
public class H2 extends GenZContainerElement {
	protected String innerText; 

	public H2() {
		this.innerText = ""; 
	}
	
	public H2(String text) {
		this.innerText = text; 
	}
	
	
	@Override
	public String render() {
		return String.format("<h2 %s>%s\n%s</h2>", attributes.render(), innerText, super.render());
	}
}
