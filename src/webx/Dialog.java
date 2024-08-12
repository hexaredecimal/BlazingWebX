package webx;

/**
 *
 * @author hexaredecimal
 */
public class Dialog extends WebXContainerElement {
	private boolean open; 
	
	public Dialog() {
		open = false;
	}

	public Dialog open() {
		this.open = true; 
		return this;
	}
	
	@Override
	public String render() {
		String opened = this.open ? "open" : ""; 
		return String.format("<dialog %s %s>\n%s</dialog>", attributes.render(), opened, super.render());
	}
}
