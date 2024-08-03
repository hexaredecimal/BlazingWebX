package genz;

/**
 *
 * @author hexaredecimal
 */
public class Label extends GenZContainerElement {
	protected String innerText; 

	public Label() {
		this.innerText = ""; 
	}
	
	public Label(String text) {
		this.innerText = text; 
	}
	
	
	@Override
	public String render() {
		return String.format("<label %s>%s\n%s</label>", attributes.render(), innerText, super.render());

	}
}
