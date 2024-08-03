package genz;

/**
 *
 * @author hexaredecimal
 */
public class Option extends GenZContainerElement {
	protected String innerText; 

	public Option() {
		this.innerText = ""; 
	}
	
	public Option(String text) {
		this.innerText = text; 
	}
	
	@Override
	public String render() {
		return String.format("<option %s>%s\n%s</option>", attributes.render(), innerText, super.render());
	}
}
