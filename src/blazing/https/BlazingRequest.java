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
  
  public static Result<String, IOException> post(String url, Map<?, ?> args) {
    return doMethodWithBody("POST", url, args);
  }

  private static Result<String, IOException> doMethodWithBody(String method, String url, Map<?, ?> args) {
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
      http.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
      http.connect();

      OutputStream os = http.getOutputStream();
      os.write(out);

      // Read the response
      int status = http.getResponseCode();
      InputStream responseStream;
      if (status >= 200 && status < 300) {
        responseStream = http.getInputStream(); // Success
      } else {
        return Result.err(new IOException("Server response: " + status));
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
      return Result.ok(res);

    } catch (IOException ex) {
      return Result.err(ex);
    }
  }
}
