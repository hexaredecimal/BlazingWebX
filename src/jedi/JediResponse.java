package jedi;

import com.sun.net.httpserver.HttpExchange;
import genz.GenZElement;
import java.io.IOException;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hexaredecimal
 */
public class JediResponse {
	private HttpExchange exchange; 
	
	public JediResponse(HttpExchange exchange) {
		this.exchange = exchange; 
	}

	public void sendUiRespose(GenZElement root) {
		this.sendResponse(root.render());
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
