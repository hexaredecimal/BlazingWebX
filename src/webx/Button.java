package webx;

/**
 *
 * @author hexaredecimal
 */
public class Button extends GenZContainerElement {
	protected String innerText; 

	public Button() {
		this.innerText = ""; 
	}
	
	public Button(String text) {
		this.innerText = text; 
	}
	
	
	@Override
	public String render() {
		return String.format("<button %s>%s\n%s</button>", attributes.render(), innerText, super.render());
	}
}
