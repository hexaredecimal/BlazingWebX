package webx;

/**
 *
 * @author hexaredecimal
 */
public class Embed extends GenZElement {
	private String path, type; 

	public Embed() {
		this.path = this.type = ""; 
	}
	
	public Embed(String path) {
		this.path = path;
		this.type = ""; 
	}
	
	public Embed(String path, String type) {
		this.path = path;
		this.type = type;
	}

	public Embed src(String path) {
		this.path = path; 
		return this;
	}

	public Embed type(String type) {
		this.type = type; 
		return this;
	}
	
	@Override
	public String render() {
		if (this.path != null) 
			this.attributes.addAttribute("src", this.path);
		
		if (this.type != null)
			this.attributes.addAttribute("type", this.type);
		
		return String.format("<embed %s />", this.attributes.render());
	}
}
