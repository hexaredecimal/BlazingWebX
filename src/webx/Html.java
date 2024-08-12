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
		this.addHeaderScript("https://unpkg.com/htmx.org@2.0.1");
	}

	public Html title(String title) {
		this.top.add(String.format("<title>%s</title>", title));
		return this;
	}
	
	public final Html addHeaderScript(String url) {
		this.top.add(String.format("<script type=\"text/javascript\" src=\"%s\"> </script>", url));
		return this;
	}

	public final Html addHeaderStyleLink(String url) {
		this.top.add(String.format("<link rel=\"stylesheet\" href=\"%s\" />", url));
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
			.append(String.format("<body %s>", this.attributes.render()).indent(4))
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
