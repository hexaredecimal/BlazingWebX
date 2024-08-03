package genz;

/**
 *
 * @author hexaredecimal
 */
public class Audio extends GenZContainerElement {

	private boolean controls, autoplay, loop, muted;
	private String src;

	private Audio src(String path) {
		this.src = path;
		return this;
	}

	public Audio controls() {
		this.controls = true;
		return this;
	}

	public Audio autoplay() {
		this.autoplay = true;
		return this;
	}

	public Audio loop() {
		this.loop = true;
		return this;
	}

	public Audio muted() {
		this.muted = true;
		return this;
	}

	@Override
	public String render() {
		StringBuilder sb = new StringBuilder();
		if (this.controls) {
			sb.append("controls");
			sb.append(" ");
		}
		if (this.autoplay) {
			sb.append("autoplay");
			sb.append(" ");
		}
		if (this.loop) {
			sb.append("loop");
			sb.append(" ");
		}
		if (this.muted) {
			sb.append("muted");
			sb.append(" ");
		}

		if (this.src != null) {
			this.attributes.addAttribute("src", this.src);
		}
		return String.format("<audio %s %s>\n%s</audio>", attributes.render(), sb.toString(), super.render());
	}
}
