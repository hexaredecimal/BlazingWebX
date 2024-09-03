package webx;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author hexaredecimal
 */
public class Html extends WebXContainerElement {

	private List<String> top;
	private List<String> bottom;

	public Html() {
		this.top = new ArrayList<>();
		this.bottom = new ArrayList<>();
		this.addHeaderChildren(
			new Meta()
				.attr("name", "viewport")
				.attr("content", "width=device-width, initial-scale=1.0")
		);
		this.addHeaderScript("https://unpkg.com/htmx.org@2.0.1");
	}

	public final Html title(String title) {
		this.top.add(String.format("<title>%s</title>", title));
		return this;
	}

	public final Html favicon(String path) {
		this.addHeaderChild(
			new Link()
				.attr("rel", "icon")
				.attr("type", "image/x-icon")
				.attr("href", path)
		);
		return this;
	}

	public final Html addHeaderChild(WebXElement element) {
		this.top.add(element.render());
		return this;
	}

	public final Html addHeaderChildren(WebXElement... elements) {
		for (var element: elements) {
			this.top.add(element.render());
		}
		return this;
	}
	
	public final Html addHeaderScript(String url) {
		this.addHeaderChild(
			new Script()
				.attr("type", "text/javascript")
				.attr("src", url)
		);
		return this;
	}

	public final Html addHeaderStyleLink(String url) {
		this.top.add(String.format("<link rel=\"stylesheet\" href=\"%s\" />", url));
		this.addHeaderChild(
			new Link()
				.attr("rel", "stylesheet")
				.attr("href", url)
		);
		return this;
	}

	@Override
	public String render() {
		final StringBuilder sb = new StringBuilder();
		sb
			.append("<!DOCTYPE html>".indent(0))
			.append("<html>".indent(0))
			.append("<head>".indent(4));


		top.forEach(element -> {
			sb.append(element.indent(6));
		});

		sb
			.append("</head>".indent(4))
			.append(String.format("<body %s>", this.attributes.render()))
			.append(super.render().indent(6));
		
		bottom.forEach(element -> {
			sb.append(element.indent(6));
		});
		
		sb
			.append("</body>".indent(4))
			.append("</html>".indent(0));

		return sb.toString();
	}
}
