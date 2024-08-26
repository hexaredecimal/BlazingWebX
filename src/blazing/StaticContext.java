package blazing;

import blazing.fs.FileSystem;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author hexaredecimal
 */
class StaticContext implements HttpHandler  {

	private static final Map<String, String> MIME_MAP = new HashMap<>();

	static {
		MIME_MAP.put("appcache", "text/cache-manifest");
		MIME_MAP.put("css", "text/css");
		MIME_MAP.put("gif", "image/gif");
		MIME_MAP.put("html", "text/html");
		MIME_MAP.put("js", "application/javascript");
		MIME_MAP.put("json", "application/json");
		MIME_MAP.put("jpg", "image/jpeg");
		MIME_MAP.put("jpeg", "image/jpeg");
		MIME_MAP.put("mp4", "video/mp4");
		MIME_MAP.put("pdf", "application/pdf");
		MIME_MAP.put("png", "image/png");
		MIME_MAP.put("svg", "image/svg+xml");
		MIME_MAP.put("xlsm", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
		MIME_MAP.put("xml", "application/xml");
		MIME_MAP.put("zip", "application/zip");
		MIME_MAP.put("md", "text/plain");
		MIME_MAP.put("txt", "text/plain");
		MIME_MAP.put("php", "text/plain");
	}


	
	private final String baseDir;

	public StaticContext(String baseDir) {
		this.baseDir = baseDir;
	}

	@Override
	public void handle(HttpExchange t) throws IOException {
		String requestedFile = t.getRequestURI().getPath().substring("/".concat(baseDir).length());
		File file = new File(baseDir + requestedFile);

		if (file.exists() && !file.isDirectory()) {
			String extension = FileSystem.fileExtension(file.getName());
			String mimeType = MIME_MAP.getOrDefault(extension, "application/octet-stream");

			t.getResponseHeaders().set("Content-Type", mimeType);
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
