package genz;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author hexaredecimal
 */
public class Html extends GenZContainerElement {

	private List<String> top;
	private List<String> bottom;

	public Html() {
		this.top = new ArrayList<>();
		this.bottom = new ArrayList<>();
	}

	public Html title(String title) {
		this.top.add(String.format("<title>%s</title>", title));
		return this;
	}
	
	public Html addHeaderScript(String url) {
		this.top.add(String.format("<script type=\"text/javascript\" src=\"%s\"> </script>", url));
		return this;
	}

	public Html addHeaderStyleLink(String url) {
		this.top.add(String.format("<link rel=\"stylesheet\" url=\"%s\"> </script>", url));
		return this;
	}

	@Override
	public String render() {
		final StringBuilder sb = new StringBuilder();
		sb.append("<html>".indent(0));
		sb.append("<head>".indent(4));

		top.forEach(element -> {
			sb.append(element);
		});

		sb.append("</head>".indent(4));
		sb.append("<body>".indent(4));
		sb.append(super.render().indent(6));
		bottom.forEach(element -> {
			sb.append(element);
		});
		sb.append("</body>".indent(4));
		sb.append("</html>".indent(0));

		return sb.toString();
	}
}
