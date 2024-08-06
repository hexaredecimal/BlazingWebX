package jedi;

import com.sun.net.httpserver.HttpExchange;
import genz.GenZElement;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hexaredecimal
 */
public class JediResponse {
	private HttpExchange exchange;
	private Map<String, String> params; 
	public JediResponse(HttpExchange exchange) {
		this.exchange = exchange;
		
		InputStream is = exchange.getRequestBody();
		try {
			String body = new String(is.readAllBytes(), StandardCharsets.UTF_8);
			this.params = parseQuery(body);
		} catch (IOException ex) {
		}
	}

	public Map<String, String> params() {
		return this.params;
	}
	
	public void sendUiRespose(GenZElement root) {
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

	public void sendResponse(String data) {
		try {
			exchange.sendResponseHeaders(200, data.length());
			OutputStream os = exchange.getResponseBody();
			os.write(data.getBytes());
			os.close();
		} catch (IOException ex) {
			Logger.getLogger(JediResponse.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

}
