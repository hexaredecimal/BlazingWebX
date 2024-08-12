package webx;

/**
 *
 * @author hexaredecimal
 */
public class Del extends GenZContainerElement {
	protected String innerText; 

	public Del() {
		this.innerText = ""; 
	}
	
	public Del(String text) {
		this.innerText = text; 
	}
	
	
	@Override
	public String render() {
		return String.format("<del %s>%s\n%s</del>", attributes.render(), innerText, super.render());

	}
}
