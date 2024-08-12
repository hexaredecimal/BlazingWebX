package webx;

/**
 *
 * @author hexaredecimal
 */
public class Progress extends WebXContainerElement {
	protected String innerText; 

	public Progress() {
		this.innerText = ""; 
	}
	
	public Progress(String text) {
		this.innerText = text; 
	}
	
	@Override
	public String render() {
		return String.format("<progress %s>%s\n%s</progress>", attributes.render(), innerText, super.render());
	}
}
