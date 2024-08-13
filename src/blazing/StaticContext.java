/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package blazing;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 *
 * @author hexaredecimal
 */
class StaticContext implements HttpHandler  {
	private final String baseDir;

	public StaticContext(String baseDir) {
		this.baseDir = baseDir;
	}

	@Override
	public void handle(HttpExchange t) throws IOException {
		String requestedFile = t.getRequestURI().getPath().substring("/".concat(baseDir).length());
		File file = new File(baseDir + requestedFile);

		if (file.exists() && !file.isDirectory()) {
			t.sendResponseHeaders(200, file.length());
			try (OutputStream os = t.getResponseBody(); FileInputStream fs = new FileInputStream(file)) {
				byte[] buffer = new byte[1024];
				int count;
				while ((count = fs.read(buffer)) != -1) {
					os.write(buffer, 0, count);
				}
			}
		} else {
			String response = "404 (Not Found)\n";
			t.sendResponseHeaders(404, response.length());
			OutputStream os = t.getResponseBody();
			os.write(response.getBytes());
			os.close();
		}
	}
}
