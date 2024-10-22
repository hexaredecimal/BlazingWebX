package webx;

/**
 * Represents a basic HTML element. All UI Nodes extend this.
 *
 * @author hexaredecimal
 */
public abstract class WebXElement {

	protected WebXAttributes attributes = new WebXAttributes();

	/**
	 * Adds an attribute to the element
	 *
	 * @param key
	 * @param value
	 * @return A modified element
	 */
	public WebXElement attr(String key, String value) {
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
	public WebXElement hxVals(String val) {
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
	public WebXElement hxTrigger(String trigger) {
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
	public WebXElement hxTarget(String target) {
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

	public WebXElement hxSwap(String target) {
		this.attr("hx-swap", target);
		return this;
	}
	
	/**
	 * The hx-swap-oob attribute allows you to specify that some content in a
	 * response should be swapped into the DOM somewhere other than the target,
	 * that is “Out of Band”. This allows you to piggy back updates to other
	 * element updates on a response.
	 * 
	 * @see <a href="https://htmx.org/attributes/hx-swap-oob/">htmx docs</a>
	 *
	 * <pre>
	 * {@code
	 * 	new Button("Click Me")
	 * 		.hxPost("/greet)
	 * 		.hxSwapOob("true");
	 * }
	 * </pre>
	 *
	 * @param trigger
	 * @return
	 */
	public WebXElement hxSwapOob(String target) {
		this.attr("hx-swap-oob", target);
		return this;
	}

	/**
	 * Submits a file uploaded via a form to the server
	 * 
	 * @see <a href="https://htmx.org/examples/file-upload/">htmx docs</a>
	 * 
	 * @param type
	 * @return 
	 */
	public WebXElement hxEncoding(String type) {
		this.attr("hx-encoding", type);
		return this;
	}

	/**
	 * Submits a file uploaded via a form to the server. 
	 * Sets the the type to "multipart/form-data"
	 * 
	 * @see <a href="https://htmx.org/examples/file-upload/">htmx docs</a>
	 * 
	 * @param type
	 * @return 
	 */
	public WebXElement hxEncoding() {
		this.attr("hx-encoding", "multipart/form-data");
		return this;
	}

	/**
	 * The hxOn method allow you to embed scripts inline to respond to events directly on an element; 
	 * similar to the onevent properties found in HTML, such as onClick.
	 * The hx-on* attributes improve upon onevent by enabling the
	 * handling of any arbitrary JavaScript event, for enhanced Locality of
	 * Behaviour (LoB) even when dealing with non-standard DOM events
	 * 
	 * @see <a href="https://htmx.org/attributes/hx-on/">htmx docs</a>
	 * 
	 * @param event
	 * @return 
	 */
	public WebXElement hxOn(String event) {
		this.attr("hx-on", event);
		return this;
	}
	
	/**
	 * The hxPost method will cause an element to issue a POST to the
	 * specified URL and swap the HTML into the DOM using a swap strategy
	 * 
	 * @see <a href="https://htmx.org/attributes/hx-post/">htmx docs</a>
	 * 
	 * @param path
	 * @return 
	 */
	public WebXElement hxPost(String path) {
		this.attr("hx-post", path);
		return this;
	}

	/**
	 * The hxGet method will cause an element to issue a GET to the specified
	 * URL and swap the HTML into the DOM using a swap strategy
	 * 
	 * @see <a href="https://htmx.org/attributes/hx-get/">htmx docs</a>
	 * 
	 * @param path
	 * @return 
	 */
	public WebXElement hxGet(String path) {
		this.attr("hx-get", path);
		return this;
	}

	/**
	 * The hxBoost attribute allows you to “boost” normal anchors and form tags to
	 * use AJAX instead.
	 * 
	 * @see <a href="https://htmx.org/attributes/hx-boost/">htmx docs</a>
	 * 
	 * @param value
	 * @return 
	 */
	public WebXElement hxBoost(String value) {
		this.attr("hx-boost", value);
		return this;
	}

	/**
	 * The hxConfirm method allows you to confirm an action before issuing a
	 * request. This can be useful in cases where the action is destructive and
	 * you want to ensure that the user really wants to do it.
	 * 
	 * @see <a href="https://htmx.org/attributes/hx-confirm/">htmx docs</a>
	 * 
	 * @param message
	 * @return 
	 */
	public WebXElement hxConfirm(String message) {
		this.attr("hx-get", message);
		return this;
	}

	public WebXElement hxExt(String ext) {
		this.attr("hx-ext", ext);
		return this;
	}

	public WebXElement hxWs(String url) {
		this.attr("hx-ws", url);
		return this;
	}

	public WebXElement hxSend(String path) {
		this.attr("hx-send", path);
		return this;
	}

	public WebXElement id(String id) {
		this.attr("id", id);
		return this;
	}


	public WebXElement className(String names) {
		this.attr("class", names);
		return this;
	}

	public abstract String render();
}
