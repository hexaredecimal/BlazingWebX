package webx;

/**
 *
 * @author hexaredecimal
 */
public class Img extends GenZElement {
	private String path, alt; 

	public Img() {
		this.path = this.alt = ""; 
	}
	
	public Img(String path) {
		this.path = path;
		this.alt = ""; 
	}
	
	public Img(String path, String alt) {
		this.path = path;
		this.alt = alt;
	}

	public Img src(String path) {
		this.path = path; 
		return this;
	}

	public Img alt(String alt) {
		this.alt = alt; 
		return this;
	}
	
	@Override
	public String render() {
		if (this.path != null) 
			this.attributes.addAttribute("src", this.path);
		
		if (this.alt != null)
			this.attributes.addAttribute("alt", this.alt);
		
		return String.format("<img %s />", this.attributes.render());
	}
}
