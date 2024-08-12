package webx;

/**
 *
 * @author hexaredecimal
 */
public class Var extends GenZContainerElement {
	protected String innerText; 

	public Var() {
		this.innerText = ""; 
	}
	
	public Var(String text) {
		this.innerText = text; 
	}
	
	@Override
	public String render() {
		return String.format("<var %s>%s\n%s</var>", attributes.render(), innerText, super.render());
	}
}
