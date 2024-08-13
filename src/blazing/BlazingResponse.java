package blazing;

import com.sun.net.httpserver.HttpExchange;
import webx.WebXElement;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The response used to respond to the client's requests. Contains information
 * such as query parameters.
 *
 * <pre>
 * {@code
 	@Route
 	public static void home(BlazingResponse response) {
 		....
 		response.sendResponse("hello, world");
 	}
 }
 * </pre>
 *
 * The snipped above send a response with the text `Hello, World` to the client
 * that made the request to `/`.
 *
 * @author hexaredecimal
 */
public class BlazingResponse {

	private HttpExchange exchange;
	private Map<String, String> params;

	public BlazingResponse(HttpExchange exchange) {
		this.exchange = exchange;

		InputStream is = exchange.getRequestBody();
		try {
			String body = new String(is.readAllBytes(), StandardCharsets.UTF_8);
			this.params = parseQuery(body);
		} catch (IOException ex) {
		}
	}

	public URI getRequestURI() {
		return this.exchange.getRequestURI();
	}

	/**
	 * Query parameters can be passed with the request without modifying the
	 * request path. Parameters are stored in the resposen object.
	 *
	 * <pre>
	 * {@code
 	@Route("/users")
 	public static void home(BlazingResponse response) {
 		Map<String, String> params = response.params();
 		String name = params.get("name");
 		if (users.contains(name)) {
 			....
 		}
 		....
 	}
 }
	 * </pre>
	 *
	 * @return Returns a Map of queries issued along with the request by the
	 * client.
	 *
	 */
	public Map<String, String> params() {
		return this.params;
	}

	/**
	 * Responds to the client with valid mark up that is ready for client side
	 * rendering.
	 *
	 * <pre>
	 * {@code
	 * 	response.sendUiResponse(
	 * 		new P("Hello, world") // <p>Hello, world</p>
	 * 	);
	 * }
	 * </pre>
	 *
	 * The snipped above response with the Paragraph element with inner text set.
	 *
	 * @param root The root node of the UI response. 
	 */
	public void sendUiRespose(WebXElement root) {
		this.sendResponse(root.render());
	}

	private Map<String, String> parseQuery(String query) throws IOException {
		Map<String, String> result = new HashMap<>();
		for (String param : query.split("&")) {
			String[] pair = param.split("=");
			if (pair.length > 1) {
				String key = URLDecoder.decode(pair[0], StandardCharsets.UTF_8.name());
				String value = URLDecoder.decode(pair[1], StandardCharsets.UTF_8.name());
				result.put(key, value);
			} else {
				String key = URLDecoder.decode(pair[0], StandardCharsets.UTF_8.name());
				result.put(key, "");
			}
		}
		return result;
	}

	/**
	 * Responds to the client with text data.
	 *
	 * <pre>
	 * {@code
	 * 		response.sendResponse("hello 1234");
	 * }
	 * </pre>
	 *
	 * The snipped above response with text data.
	 *
	 * @param data The actual data being sent to the client.
	 */
	public void sendResponse(String data) {
		try {
			var bytes = data.getBytes();
			exchange.sendResponseHeaders(200, bytes.length);
			OutputStream os = exchange.getResponseBody();
			os.write(bytes);
			os.close();
		} catch (IOException ex) {
			Logger.getLogger(BlazingResponse.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

}
