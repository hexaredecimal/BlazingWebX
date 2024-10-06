package blazing.https;

import blazing.https.query.Query;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.Map;

import blazing.types.Result;
import java.io.IOException;

/**
 *
 * @author ERC
 */
public class BlazingRequest {

	/**
	 * Sends a POST request to the target url with the args as a payload. 
	 * The default content type is "application/x-www-form-urlencoded; charset=UTF-8"
	 * @param url 
	 * @param args
	 * @return a Result Object that contains the data response or an IOException if there was an error. 
	 * Status 404 is also an error.
	 */
	public static Result<String, IOException> post(String url, Map<?, ?> args) {
		return doMethodWithBody("POST", url, "application/x-www-form-urlencoded; charset=UTF-8", args);
	}

	/**
	 * Sends a POST request to the target url with the args string as a payload. 
	 * The default content type is "application/x-www-form-urlencoded; charset=UTF-8"
	 * @param url 
	 * @param args
	 * @return a Result Object that contains the data response or an IOException if there was an error. 
	 * Status 404 is also an error.
	 */
	public static Result<String, IOException> post(String url, String args) {
		return doMethodWithBody("POST", url, "application/x-www-form-urlencoded; charset=UTF-8", args);
	}
	
	/**
	 * Sends a POST request to the target url with the args as a payload and allows for a specific content type
	 * @param url
	 * @param content_type
	 * @param args
	 * @return a Result Object that contains the data response or an IOException if there was an error. 
	 * Status 404 is also an error.
	 */
	public static Result<String, IOException> post(String url, String content_type, Map<?, ?> args) {
		return doMethodWithBody("POST", url, content_type, args);
	}
	
	/**
	 * Sends a POST request to the target url with the args string as a payload and allows for a specific content type
	 * @param url
	 * @param content_type
	 * @param args
	 * @return a Result Object that contains the data response or an IOException if there was an error. 
	 * Status 404 is also an error.
	 */
	public static Result<String, IOException> post(String url, String content_type, String args) {
		return doMethodWithBody("POST", url, content_type, args);
	}

	/**
	 * Sends a PUT request to the target url with the args as a payload. 
	 * The default content type is "application/x-www-form-urlencoded; charset=UTF-8"
	 * @param url 
	 * @param args
	 * @return a Result Object that contains the data response or an IOException if there was an error. 
	 * Status 404 is also an error.
	 */
	public static Result<String, IOException> put(String url, Map<?, ?> args) {
		return doMethodWithBody("PUT", url, "application/x-www-form-urlencoded; charset=UTF-8", args);
	}
	
	/**
	 * Sends a PUT request to the target url with the args as a payload and allows for a specific content type
	 * @param url
	 * @param content_type
	 * @param args
	 * @return a Result Object that contains the data response or an IOException if there was an error. 
	 * Status 404 is also an error.
	 */
	public static Result<String, IOException> put(String url, String content_type, Map<?, ?> args) {
		return doMethodWithBody("PUT", url, content_type, args);
	}

	/**
	 * Sends a DELETE request to the target url
	 * @param url
	 * @return a Result Object that contains the data response or an IOException if there was an error. 
	 * Status 404 is also an error.
	 */
	public static Result<String, IOException> delete(String url) {
		return doMethodWithoutBody("DELETE", url);
	}

	/**
	 * Sends a GET request to the target url
	 * @param url
	 * @return a Result Object that contains the data response or an IOException if there was an error. 
	 * Status 404 is also an error.
	 */
	public static Result<String, IOException> get(String urlString) {
		return doMethodWithoutBody("GET", urlString);
	}

	
	/**
	 * Sends a PATCH request to the target url with the args as a payload. 
	 * The default content type is "application/x-www-form-urlencoded; charset=UTF-8"
	 * @param url 
	 * @param args
	 * @return a Result Object that contains the data response or an IOException if there was an error. 
	 * Status 404 is also an error.
	 */
	public static Result<String, IOException> patch(String url, Map<?, ?> args) {
		return doMethodWithBody("PATCH", url, "application/x-www-form-urlencoded; charset=UTF-8", args);
	}


	/**
	 * Sends a PATCH request to the target url with the args as a payload and allows for a specific content type
	 * @param url
	 * @param content_type
	 * @param args
	 * @return a Result Object that contains the data response or an IOException if there was an error. 
	 * Status 404 is also an error.
	 */
	public static Result<String, IOException> patch(String url, String content_type, Map<?, ?> args) {
		return doMethodWithBody("PATCH", url, content_type, args);
	}

	/**
	 * Sends a HEAD request to the target url
	 * @param url
	 * @return a Result Object that contains the data response or an IOException if there was an error. 
	 * Status 404 is also an error.
	 */
	public static Result<String, IOException> head(String urlString) {
		return doMethodWithoutBody("HEAD", urlString);
	}

	private static Result<String, IOException> doMethodWithBody(String method, String url, String content_type, Map<?, ?> args) {
		try {
			var server = new URL(url);
			URLConnection con = server.openConnection();
			HttpURLConnection http = (HttpURLConnection) con;
			http.setRequestMethod(method.toUpperCase());
			http.setDoOutput(true);
			String results = Query.from(args);
			byte[] out = results.getBytes(StandardCharsets.UTF_8);
			int length = out.length;
			http.setFixedLengthStreamingMode(length);

			// Allow the user to set the content properties
			http.setRequestProperty("Content-Type", content_type);
			http.connect();

			OutputStream os = http.getOutputStream();
			os.write(out);

			// Read the response
			int status = http.getResponseCode();
			InputStream responseStream;

			boolean isError = false;
			if (status >= 200 && status < 300) {
				responseStream = http.getInputStream(); // Success
			} else {
				isError = true;
				responseStream = http.getErrorStream();
			}

			BufferedReader in = new BufferedReader(new InputStreamReader(responseStream, StandardCharsets.UTF_8));
			String inputLine;
			StringBuilder response = new StringBuilder();
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}

			in.close();

			// Print the response
			String res = response.toString();
			http.disconnect();
			if (isError) {
				return Result.err(new IOException("Error(" + status + ") with message: " + res));
			}
			return Result.ok(res);

		} catch (IOException ex) {
			return Result.err(ex);
		}
	}

	private static Result<String, IOException> doMethodWithBody(String method, String url, String content_type, String args) {
		try {
			var server = new URL(url);
			URLConnection con = server.openConnection();
			HttpURLConnection http = (HttpURLConnection) con;
			http.setRequestMethod(method.toUpperCase());
			http.setDoOutput(true);
			String results = args;
			byte[] out = results.getBytes(StandardCharsets.UTF_8);
			int length = out.length;
			http.setFixedLengthStreamingMode(length);

			// Allow the user to set the content properties
			http.setRequestProperty("Content-Type", content_type);
			http.connect();

			OutputStream os = http.getOutputStream();
			os.write(out);

			// Read the response
			int status = http.getResponseCode();
			InputStream responseStream;

			boolean isError = false;
			if (status >= 200 && status < 300) {
				responseStream = http.getInputStream(); // Success
			} else {
				isError = true;
				responseStream = http.getErrorStream();
			}

			BufferedReader in = new BufferedReader(new InputStreamReader(responseStream, StandardCharsets.UTF_8));
			String inputLine;
			StringBuilder response = new StringBuilder();
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}

			in.close();

			// Print the response
			String res = response.toString();
			http.disconnect();
			if (isError) {
				return Result.err(new IOException("Error(" + status + ") with message: " + res));
			}
			return Result.ok(res);

		} catch (IOException ex) {
			return Result.err(ex);
		}
	}

	
	private static Result<String, IOException> doMethodWithoutBody(String method, String urlString) {
		HttpURLConnection http = null;
		try {
			// Create a URL object with the target URL
			URL url = new URL(urlString);
			http = (HttpURLConnection) url.openConnection();
			http.setRequestMethod(method.toUpperCase());

			// Get the response
			int status = http.getResponseCode();
			InputStream responseStream;

			boolean isError = false;
			if (status == HttpURLConnection.HTTP_OK || status == HttpURLConnection.HTTP_NO_CONTENT) {
				responseStream = http.getInputStream();
			} else {
				isError = true;
				responseStream = http.getErrorStream();
			}

			// Process the response stream
			BufferedReader in = new BufferedReader(new InputStreamReader(responseStream, StandardCharsets.UTF_8));
			String inputLine;
			StringBuilder response = new StringBuilder();
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			String res = response.toString();
			http.disconnect();

			if (isError) {
				return Result.err(new IOException("Error(" + status + ") with message: " + res));
			}

			return Result.ok(res);

		} catch (IOException ex) {
			if (http != null) {
				http.disconnect();
			}
			return Result.err(ex);
		}
	}
}
