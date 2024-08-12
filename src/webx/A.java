package webx;

/**
 *
 * @author hexaredecimal
 */
public class A extends GenZContainerElement {
	protected String innerText; 

	public A() {
		this.innerText = ""; 
	}
	
	public A(String text) {
		this.innerText = text; 
	}

	public A href(String url) {
		this.attributes.addAttribute("href", url);
		return this;
	}
	
	
	@Override
	public String render() {
		return String.format("<a %s>%s\n%s</a>", attributes.render(), innerText, super.render());

	}
}
