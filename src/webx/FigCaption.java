package webx;

/**
 *
 * @author hexaredecimal
 */
public class FigCaption extends GenZContainerElement {
	protected String innerText; 

	public FigCaption() {
		this.innerText = ""; 
	}
	
	public FigCaption(String text) {
		this.innerText = text; 
	}
	
	@Override
	public String render() {
		return String.format("<figcaption %s>%s\n%s</figcaption>", attributes.render(), innerText, super.render());
	}
}
