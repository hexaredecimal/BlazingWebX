package blazing;

import blazing.lambda.BlazingStreamFunction;
import blazing.types.Result;
import com.sun.net.httpserver.HttpExchange;
import java.io.File;
import webx.WebXElement;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
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
	private Map<String, byte[]> files;
	private byte[] bytes;

	public BlazingResponse(HttpExchange exchange) {
		this.exchange = exchange;

		InputStream is = exchange.getRequestBody();
		try {
			this.bytes = is.readAllBytes();
			this.files = new HashMap<>();
			this.params = new HashMap<>();
			String contentType = exchange.getRequestHeaders().getFirst("Content-Type");
			if (contentType == null || !contentType.contains("multipart/form-data")) {
				String body = new String(bytes, StandardCharsets.UTF_8);
				this.params = parseQuery(body).unwrapOr(new HashMap<>());
			} else {
				var result = parseFiles();
				if (result.isErr()) {
					BlazingLog.severe(result.unwrapErr().getMessage());
				}
			}
		} catch (IOException ex) {
			BlazingLog.severe(ex.getMessage());
		}
	}

	public URI getRequestURI() {
		return this.exchange.getRequestURI();
	}

	private Result<Boolean, IOException> parseFiles() {
		if ("POST".equals(exchange.getRequestMethod())) {
			String contentType = exchange.getRequestHeaders().getFirst("Content-Type");

			if (contentType != null && contentType.contains("multipart/form-data")) {
				// Boundary extraction from contentType header
				String boundary = contentType.split("boundary=")[1].trim();
				String boundaryPrefix = "--" + boundary;

				// Read the request body as bytes
				byte[] requestBodyBytes = this.bytes;

				// Split the body based on the boundary
				String bodyString = new String(requestBodyBytes, StandardCharsets.ISO_8859_1);
				String[] parts = bodyString.split(boundaryPrefix);

				// Process each part (fields and files)
				for (String part : parts) {
					// Check if the part is not empty
					if (part.trim().isEmpty()) {
						continue; // Skip empty parts
					}

					// Ensure we treat this part as bytes
					byte[] partBytes = part.getBytes(StandardCharsets.ISO_8859_1);

					// Convert part to string for header extraction
					String partString = new String(partBytes, StandardCharsets.ISO_8859_1);
					if (partString.contains("Content-Disposition: form-data")) {
						// Extract filename
						String filename = extractFileName(partString);

						if (filename != null) {
							// Find the starting point of the binary data
							int startIndexOfContent = partString.indexOf("\r\n\r\n") + 4; // Skip headers and newlines

							// Get the raw bytes for the file content
							byte[] fileContent = Arrays.copyOfRange(partBytes, startIndexOfContent, partBytes.length);
							this.files.put(filename, fileContent);
						} else {
							// This is a regular field
							String name = extractFieldName(partString);
							int startIndexOfContent = partString.indexOf("\r\n\r\n") + 4; // Skip headers and newlines
							byte[] fieldContent = Arrays.copyOfRange(partBytes, startIndexOfContent, partBytes.length);
							this.params.put(name, new String(fieldContent).trim());
						}
					}
				}

				return Result.ok(true);
			} else {
				return Result.err(new IOException("Invalid request type, files can only be uploaded using a POST request"));
			}
		}
		return null;
	}

	private String extractFieldName(String part) {
		// Find the position of name=
		int startIndex = part.indexOf("name=");
		if (startIndex != -1) {
			startIndex += "name=".length();
			int firstQuoteIndex = part.indexOf('"', startIndex);
			int secondQuoteIndex = part.indexOf('"', firstQuoteIndex + 1);
			if (firstQuoteIndex != -1 && secondQuoteIndex != -1) {
				return part.substring(firstQuoteIndex + 1, secondQuoteIndex);
			}
		}
		return null;
	}

	private String extractFileName(String part) {
		String fileName = null;

		// Find the position of filename=
		int startIndex = part.indexOf("filename=");
		if (startIndex != -1) {
			// The filename value starts after 'filename=' (which is 9 characters)
			startIndex += "filename=".length();

			// The value is usually enclosed in double quotes, so find the positions of the quotes
			int firstQuoteIndex = part.indexOf('"', startIndex);
			int secondQuoteIndex = part.indexOf('"', firstQuoteIndex + 1);

			// Extract the filename between the quotes
			if (firstQuoteIndex != -1 && secondQuoteIndex != -1) {
				fileName = part.substring(firstQuoteIndex + 1, secondQuoteIndex);
			}
		}

		return fileName;
	}

	/**
	 * Query parameters can be passed with the request without modifying the
	 * request path. Parameters are stored in the response object.
	 *
	 * <pre>
	 * {@code
	 * @Route("/users")
	 * public static void home(BlazingResponse response) {
	 * 	Map<String, String> params = response.params();
	 * 	String name = params.get("name");
	 * 	if (users.contains(name)) {
	 * 		....
	 * 	}
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
	 * Holds the files parsed from a multipart/form-data form
	 *
	 * <pre>
	 * {@code
	 * @Post("/v1/post/upload")
	 * public static void home(BlazingResponse response) {
	 * 	Map<String, byte[]> files = response.files();
	 * }
	 * }
	 * </pre>
	 *
	 * @return Returns a Map of file names and their binary representations as
	 * binary data
	 */
	public Map<String, byte[]> files() {
		return this.files;
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
		}
		return Result.ok(result);
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
			var _bytes = data.getBytes();
			exchange.sendResponseHeaders(200, _bytes.length);
			os.write(_bytes);
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
			var _bytes = data.getBytes();
			exchange.sendResponseHeaders(status, _bytes.length);
			os.write(_bytes);
		} catch (IOException ex) {
			BlazingLog.severe(ex.getMessage());
		}
	}

	/**
	 * Sets response headers for client access. Useful when using BlazingWebX for backend data APIs
	 * and clients have to access the data from different ports or domain. 
	 * @param key
	 * @param value 
	 */
	public void setHeader(String key, String value) {
		exchange.getResponseHeaders().add(key, value);
	}

	/**
	 * When you make requests with non-simple HTTP methods (e.g., POST, PUT,
	 * DELETE), or if you’re sending custom headers (e.g., Authorization), the
	 * browser sends an OPTIONS request (preflight) first. The server needs to
	 * respond to this with appropriate CORS headers.
	 * @return 
	 */
	public boolean isPreFlight() {
		String requestMethod = exchange.getRequestMethod();
		return "OPTIONS".equalsIgnoreCase(requestMethod);
	}

	/**
	 * Predefined response for the preflight, which sends a 204 header with no body
	 * to the client as the Preflight response
	 * @return 
	 */
	public Result<Boolean, IOException> noContent() {
		try {
			exchange.sendResponseHeaders(204, -1);  // No content for OPTIONS
			return Result.ok(true);
		} catch (IOException ex) {
			return Result.err(ex);
		} 
	}

	/**
	 * Helper method for setting up the open CORS for all clients. 
	 * NOTE: Please use this method when testing. When deploying for production 
	 * please use setHeader and set the appropriate CORS
	 */
	public void defaultCORSHeaders() {
		setHeader("Access-Control-Allow-Origin", "*");
		setHeader("Access-Control-Allow-Methods", "GET, POST, OPTIONS");
		setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");
	}

	/**
	 * Sends a response along with the an array of bytes as a response to a request.
	 * @param statusCode
	 * @param responseBytes
	 * @return 
	 */
	public Result<Boolean, IOException> sendResponse(int statusCode, byte[] responseBytes) {
		try (OutputStream os = exchange.getResponseBody()) {
			exchange.sendResponseHeaders(statusCode, responseBytes.length);
			os.write(responseBytes);
			return Result.ok(true);
		} catch (IOException ex) {
			return Result.err(ex);
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

	/**
	 * Streams a file to the client for download purposes, the default content
	 * type is "application/octet-stream"
	 *
	 * {@code
	 * 	response.streamFile("Vid.mp4", (outstream, file) -> {
	 * 	try (FileInputStream fileInputStream = new FileInputStream(file)) {
	 * 		byte[] buffer = new byte[4096];
	 * 		int bytesRead;
	 * 		while ((bytesRead = fileInputStream.read(buffer)) != -1) {
	 * 			outstream.write(buffer, 0, bytesRead);
	 * 		}
	 * 		outstream.flush();
	 * 	} catch (FileNotFoundException ex) {
	 * 		BlazingLog.severe(ex.getMessage());
	 * 	} catch (IOException ex) {
	 * 		BlazingLog.severe(ex.getMessage());
	 * 	}
	 * }); 
	 * }
	 *
	 * @param filename
	 * @param callback
	 */
	public void streamFile(String filename, BlazingStreamFunction callback) {
		File fp = new File(filename);
		setHeader("Content-Type", "application/octet-stream");
		setHeader("Content-Disposition", "attachment; filename=\"" + fp.getName() + "\"");
		try {
			exchange.sendResponseHeaders(200, 0); // Size = 0 because we are streaming the file dynamically
			try (OutputStream out = exchange.getResponseBody()) {
				callback.stream(out, fp);
			}
		} catch (IOException ex) {
			BlazingLog.severe(ex.getMessage());
		}
	}

	/**
	 * Streams a file to the client for download purposes
	 *
	 * {@code
	 * 	response.streamFile("Archive.zip", "application/zip", (outstream, file) -> {
	 *	try (FileInputStream fileInputStream = new FileInputStream(file)) {
	 * 		byte[] buffer = new byte[4096];
	 * 		int bytesRead;
	 * 		while ((bytesRead = fileInputStream.read(buffer)) != -1) {
	 * 			outstream.write(buffer, 0, bytesRead);
	 * 		}
	 * 		outstream.flush();
	 * 	} catch (FileNotFoundException ex) {
	 * 		BlazingLog.severe(ex.getMessage());
	 * 	} catch (IOException ex) {
	 * 		BlazingLog.severe(ex.getMessage());
	 * }
	 * 	});
	 * }
	 * @param filename
	 * @param stream_type
	 * @param callback
	 */
	public void streamFile(String filename, String stream_type, BlazingStreamFunction callback) {
		File fp = new File(filename);
		setHeader("Content-Type", stream_type);
		setHeader("Content-Disposition", "attachment; filename=\"" + fp.getName() + "\"");
		try {
			exchange.sendResponseHeaders(200, 0); // Size = 0 because we are streaming the file dynamically
			try (OutputStream out = exchange.getResponseBody()) {
				callback.stream(out, fp);
			}
		} catch (IOException ex) {
			BlazingLog.severe(ex.getMessage());
		}
	}

	/**
	 * Streams a file with a size that is known upfront. This is useful for
	 * sending small files such as .pdf documents, etc. Sets the content type to
	 * "application/octet-stream" as the default value
	 *
	 * @param filename
	 * @param callback
	 */
	public void streamSizedFile(String filename, BlazingStreamFunction callback) {
		File fp = new File(filename);
		setHeader("Content-Type", "application/octet-stream");
		setHeader("Content-Disposition", "attachment; filename=\"" + fp.getName() + "\"");
		try {
			exchange.sendResponseHeaders(200, fp.length());
			try (OutputStream out = exchange.getResponseBody()) {
				callback.stream(out, fp);
			}
		} catch (IOException ex) {
			BlazingLog.severe(ex.getMessage());
		}
	}

	/**
	 * Streams a file with a size that is known upfront. This is useful for
	 * sending small files such as .pdf documents, etc.
	 *
	 * @param filename
	 * @param stream_type
	 * @param callback
	 */
	public void streamSizedFile(String filename, String stream_type, BlazingStreamFunction callback) {
		File fp = new File(filename);
		setHeader("Content-Type", "application/pdf");
		setHeader("Content-Disposition", "attachment; filename=\"" + fp.getName() + "\"");
		try {
			exchange.sendResponseHeaders(200, fp.length());
			try (OutputStream out = exchange.getResponseBody()) {
				callback.stream(out, fp);
			}
		} catch (IOException ex) {
			BlazingLog.severe(ex.getMessage());
		}
	}
}
