package webx;

/**
 *
 * @author hexaredecimal
 */
public class Pre extends WebXContainerElement {
	protected String innerText; 

	public Pre() {
		this.innerText = ""; 
	}
	
	public Pre(String text) {
		this.innerText = text; 
	}
	
	@Override
	public String render() {
		return String.format("<pre %s>%s\n%s</pre>", attributes.render(), innerText, super.render());
	}
}
