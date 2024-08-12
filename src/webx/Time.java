package webx;

/**
 *
 * @author hexaredecimal
 */
public class Time extends WebXContainerElement {
	protected String innerText; 

	public Time() {
		this.innerText = ""; 
	}
	
	public Time(String text) {
		this.innerText = text; 
	}
	
	@Override
	public String render() {
		return String.format("<time %s>%s\n%s</time>", attributes.render(), innerText, super.render());
	}
}
