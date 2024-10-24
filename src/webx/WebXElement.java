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
	 * @param key The attribute identifier or name
	 * @param value The attribute value
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
	 * @param val Custom data to be send to the server along with the request. 
	 * Useful when sending post requests. 
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
	 * @param trigger The event that triggers the http request
	 * @return A modified element
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
	 * @param target The target element to be replaced with the response html
	 * @return A modified element
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
	 * @param strategy The swap strategy to be used
	 * @return A modified element
	 */

	public WebXElement hxSwap(String strategy) {
		this.attr("hx-swap", strategy);
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
	 * @param value Activation (true) or a swap strategy
	 * @return A modified element
	 */
	public WebXElement hxSwapOob(String value) {
		this.attr("hx-swap-oob", value);
		return this;
	}

	/**
	 * Submits a file uploaded via a form to the server
	 * 
	 * @see <a href="https://htmx.org/examples/file-upload/">htmx docs</a>
	 * 
	 * @param type The type of encoding which must be used. 
	 * Note custom encoding formats are not yet supported for now.
	 * @return A modified element 
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
	 * @return A modified element 
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
	 * @param event Event which triggers the requests
	 * @return A modified element 
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
	 * @param path Server endpoint to make the request to
	 * @return A modified element 
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
	 * @param path Server endpoint to make the request to
	 * @return A modified element 
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
	 * @param activate The boolean value used to enable or disable the boost
	 * @return A modified element 
	 */
	public WebXElement hxBoost(boolean activate) {
		this.attr("hx-boost", String.valueOf(activate));
		return this;
	}

	/**
	 * The hxConfirm method allows you to confirm an action before issuing a
	 * request. This can be useful in cases where the action is destructive and
	 * you want to ensure that the user really wants to do it.
	 * 
	 * @see <a href="https://htmx.org/attributes/hx-confirm/">htmx docs</a>
	 * 
	 * @param message Text to be used as a confirmation message
	 * @return A modified element 
	 */
	public WebXElement hxConfirm(String message) {
		this.attr("hx-get", message);
		return this;
	}

	/**
	 * The hxExt attribute enables an htmx extension for an element and all its
	 * children.
	 *
	 * The value can be a single extension name or a comma separated list of
	 * extensions to apply.
	 *
	 * The hxExt tag may be placed on parent elements if you want a plugin to
	 * apply to an entire swath of the DOM, and on the body tag for it to apply to
	 * all htmx requests.
	 * 
	 * @see <a href="https://htmx.org/attributes/hx-ext/">htmx docs</a>
	 * 
	 * @param ext The extension to be used
	 * @return A modified element 
	 */
	public WebXElement hxExt(String ext) {
		this.attr("hx-ext", ext);
		return this;
	}

	/**
	 * The WebSockets extension enables easy, bi-directional communication with
	 * Web Sockets servers directly from HTML.
	 * 
	 * @see <a href="https://htmx.org/extensions/web-sockets/">htmx docs</a>
	 * 
	 * @param url Url for the websocket server
	 * @return A modified element 
	 */
	public WebXElement hxWs(String url) {
		this.attr("hx-ws", url);
		return this;
	}

	/**
	 * Sends a message to the nearest websocket based on the trigger value for the
	 * element
	 * 
	 * @see <a href="https://htmx.org/extensions/web-sockets/">htmx docs</a>
	 * 
	 * @return A modified element 
	 */
	public WebXElement hxSend() {
		this.attr("hx-send", "");
		return this;
	}

	/**
	 * The id global attribute defines an identifier (ID) which must be unique in
	 * the whole document.
	 * 
	 * The purpose of the ID attribute is to identify a single element when
	 * linking (using a fragment identifier), scripting, or styling (with CSS).
	 *
	 * Elements with ID attributes are available as global properties. The
	 * property name is the ID attribute, and the property value is the element.
	 * 
	 * @see <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Global_attributes/id">htmx docs</a>
	 * 
	 * @param id The identifier for the element
	 * @return A modified element 
	 */
	public WebXElement id(String id) {
		this.attr("id", id);
		return this;
	}

	/**
	 * The class global attribute is a list of the classes of the element,
	 * separated by ASCII whitespace.
	 * 
	 * Classes allow CSS and JavaScript to select and access specific elements via
	 * the class selectors or functions like the
	 * document.getElementsByClassName().
	 * 
	 * @see <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Global_attributes/class">htmx docs</a>
	 * 
	 * @param names Whitespace separated list of classes
	 * @return A modified element 
	 */
	public WebXElement className(String names) {
		this.attr("class", names);
		return this;
	}

	/**
	 * Converts the Any webx element into its html representation, including all its children, recursively. 
	 * 
	 * @return The String representation of the element along with the children.
	 */
	public abstract String render();
}
