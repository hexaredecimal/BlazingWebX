package genz;

/**
 *
 * @author hexaredecimal
 */
public class Sub extends GenZContainerElement {
	protected String innerText; 

	public Sub() {
		this.innerText = ""; 
	}
	
	public Sub(String text) {
		this.innerText = text; 
	}
	
	@Override
	public String render() {
		return String.format("<sub %s>%s\n%s</sub>", attributes.render(), innerText, super.render());

	}
}
