package genz;

/**
 * Represents a basic HTML element. All UI Nodes extend this.
 *
 * @author hexaredecimal
 */
public abstract class GenZElement {

	protected GenZAttributes attributes = new GenZAttributes();

	/**
	 * Adds an attribute to the element
	 *
	 * @param key
	 * @param value
	 * @return A modified element
	 */
	public GenZElement attr(String key, String value) {
		this.attributes.addAttribute(key, value);
		return this;
	}

	/**
	 * The hx-vals attribute allows you to add to the parameters that will be
	 * submitted with an AJAX request.
	 *
	 * @see <a href="https://htmx.org/attributes/hx-vals/">htmx docs</a>
	 *
	 * <pre>
	 * {@code
	 * 	new P()
	 * 		.hxVals("{\"myVal\": 100}");
	 * 	new P()
	 * 		.hxVals("js:{\"compute\": 100 * 5 * somevar}");
	 * }
	 * </pre>
	 *
	 * @param val
	 * @return A modified element
	 */
	public GenZElement hxVals(String val) {
		this.attr("hx-vals", val);
		return this;
	}

	/**
	 * The hx-trigger attribute allows you to specify what triggers an AJAX
	 * request.
	 *
	 * @see <a href="https://htmx.org/attributes/hx-trigger/">htmx docs</a>
	 *
	 * <pre>
	 * {@code
	 * 	new P("00:00:00")
	 * 		.hxPost("/get_clock")
	 * 		.hxTrigger("every 1s");
	 * }
	 * </pre>
	 *
	 * @param trigger
	 * @return
	 */
	public GenZElement hxTrigger(String trigger) {
		this.attr("hx-trigger", trigger);
		return this;
	}

	/**
	 * The hx-target attribute allows you to target a different element for
	 * swapping than the one issuing the AJAX request.
	 *
	 * @see <a href="https://htmx.org/attributes/hx-target/">htmx docs</a>
	 *
	 * <pre>
	 * {@code
	 * 	new Button("Click Me")
	 * 		.hxPost("/greet)
	 * 		.hxTarget("#parentdiv");
	 * }
	 * </pre>
	 *
	 * @param trigger
	 * @return
	 */
	public GenZElement hxTarget(String target) {
		this.attr("hx-target", target);
		return this;
	}

	/**
	 * The hx-swap attribute allows you to specify how the response will be
	 * swapped in relative to the target of an AJAX request. If you do not specify
	 * the option, the default is htmx.config.defaultSwapStyle (innerHTML).
	 *
	 * @see <a href="https://htmx.org/attributes/hx-swap/">htmx docs</a>
	 *
	 * <pre>
	 * {@code
	 * 	new Button("Click Me")
	 * 		.hxPost("/greet)
	 * 		.hxSwap("outerHTML");
	 * }
	 * </pre>
	 *
	 * @param trigger
	 * @return
	 */
	public GenZElement hxSwap(String target) {
		this.attr("hx-swap", target);
		return this;
	}

	public GenZElement hxPost(String path) {
		this.attr("hx-post", path);
		return this;
	}

	public GenZElement hxGet(String path) {
		this.attr("hx-get", path);
		return this;
	}

	public GenZElement id(String id) {
		this.attr("id", id);
		return this;
	}

	public GenZElement className(String names) {
		this.attr("class", names);
		return this;
	}

	public abstract String render();
}
