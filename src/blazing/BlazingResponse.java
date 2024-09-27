package blazing;

import blazing.types.Result;
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
 * @Route
 * public static void home(BlazingResponse response) {
 * ....
 * response.sendResponse("hello, world");
 * }
 * }
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
			this.params = parseQuery(body).unwrapOr(new HashMap<>());
		} catch (IOException ex) {
			BlazingLog.severe(ex.getMessage());
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
	 * @Route("/users")
	 * public static void home(BlazingResponse response) {
	 * Map<String, String> params = response.params();
	 * String name = params.get("name");
	 * if (users.contains(name)) {
	 * ....
	 * }
	 * ....
	 * }
	 * }
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

	private Result<Map<String, String>, IOException> parseQuery(String query) {
		Map<String, String> result = new HashMap<>();
		try {
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
		} catch (IOException ex) {
			return Result.err(ex);
		} finally {
			return Result.ok(result);
		}
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
		try (OutputStream os = exchange.getResponseBody();) {
			var bytes = data.getBytes();
			exchange.sendResponseHeaders(200, bytes.length);
			os.write(bytes);
		} catch (IOException ex) {
			BlazingLog.severe(ex.getMessage());
		}
	}

	/**
	 * Responds to the client with text data attached to a status code.
	 *
	 * <pre>
	 * {@code
	 * 		response.sendResponse(404, "hello 1234");
	 * }
	 * </pre>
	 *
	 * The snipped above response with text data.
	 *
	 * @param status The status code sent to the client
	 * @param data The actual data being sent to the client.
	 */
	public void sendResponse(int status, String data) {
		try (OutputStream os = exchange.getResponseBody();) {
			var bytes = data.getBytes();
			exchange.sendResponseHeaders(status, bytes.length);
			os.write(bytes);
		} catch (IOException ex) {
			BlazingLog.severe(ex.getMessage());
		}
	}

	/**
	 * Sends a status message to the client
	 *
	 * <pre>
	 * {@code
	 * 		response.sendStatus(404);
	 * }
	 * </pre>
	 *
	 * @param status The status code sent to the client
	 */
	public void sendStatus(int status) {
		try {
			exchange.sendResponseHeaders(status, -1);
		} catch (IOException ex) {
			BlazingLog.severe(ex.getMessage());
		}
	}
}
