package webx;

/**
 *
 * @author hexaredecimal
 */
public class Script extends WebXElement {
	private String path, type; 
	private String code; 
	public Script() {
		this.code = "";
		this.path = "";
		this.type = "text/javascript"; 
	}
	
	public Script(String code) {
		this.code = code;
		this.path = "";
		this.type = "text/javascript"; 
	}

  public Script(String code, String src, String type) {
    this.code = code; 
    this.path = src; 
    this.type = type;
  }

	public Script src(String path) {
		this.path = path; 
		return this;
	}

	public Script type(String alt) {
		this.type = alt; 
		return this;
	}
	
	@Override
	public String render() {
		if (!this.path.isEmpty()) 
			this.attributes.addAttribute("src", this.path);
		
		if (!this.type.isEmpty())
			this.attributes.addAttribute("type", this.type);
		
		return String.format("<script %s> %s </script>", this.attributes.render(), this.code);
	}
}
