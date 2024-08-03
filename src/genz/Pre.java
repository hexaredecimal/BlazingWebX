package genz;

/**
 *
 * @author hexaredecimal
 */
public class Pre extends GenZContainerElement {
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
