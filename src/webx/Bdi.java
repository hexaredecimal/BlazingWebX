package webx;

/**
 *
 * @author hexaredecimal
 */
public class Bdi extends WebXContainerElement {
	protected String innerText; 

	public Bdi() {
		this.innerText = ""; 
	}
	
	public Bdi(String text) {
		this.innerText = text; 
	}
	
	@Override
	public String render() {
		return String.format("<bdi %s>%s\n%s</bdi>", attributes.render(), innerText, super.render());

	}
}
