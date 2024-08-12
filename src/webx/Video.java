package webx;

/**
 *
 * @author hexaredecimal
 */
public class Video extends GenZContainerElement {

	private boolean controls, autoplay, loop, muted;
	private String src;

	private Video src(String path) {
		this.src = path;
		return this;
	}

	public Video controls() {
		this.controls = true;
		return this;
	}

	public Video autoplay() {
		this.autoplay = true;
		return this;
	}

	public Video loop() {
		this.loop = true;
		return this;
	}

	public Video muted() {
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
		return String.format("<video %s %s>\n%s</video>", attributes.render(), sb.toString(), super.render());
	}
}
