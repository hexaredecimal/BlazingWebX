package webx;

/**
 *
 * @author hexaredecimal
 */
public class Meter extends GenZContainerElement {
	protected String innerText; 

	public Meter() {
		this.innerText = ""; 
	}
	
	public Meter(String text) {
		this.innerText = text; 
	}
	
	@Override
	public String render() {
		return String.format("<meter %s>%s\n%s</meter>", attributes.render(), innerText, super.render());
	}
}
