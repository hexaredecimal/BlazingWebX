package genz;

/**
 *
 * @author hexaredecimal
 */
public class Cite extends GenZContainerElement {
	protected String innerText; 

	public Cite() {
		this.innerText = ""; 
	}
	
	public Cite(String text) {
		this.innerText = text; 
	}
	
	
	@Override
	public String render() {
		return String.format("<cite %s>%s\n%s</cite>", attributes.render(), innerText, super.render());

	}
}
