package blazing.https.query;

import blazing.types.Result;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;
import java.util.StringJoiner;

/**
 *
 * @author ERC
 */
public class Query {

	/**
	 * Creates a valid query string from a hashmap in the form key1=value&key2=value2
	 * @param args
	 * @return A Result Object which contains a String if successful and an 
	 * UnsupportedEncodingException if not.
	 */
	public static Result<String, UnsupportedEncodingException> from(Map<?, ?> args) {
		StringJoiner sj = new StringJoiner("&");
		try {
			for (Map.Entry<?, ?> entry : args.entrySet()) {
				sj.add(URLEncoder.encode(entry.getKey().toString(), "UTF-8") + "="
					+ URLEncoder.encode(entry.getValue().toString(), "UTF-8"));
			}

			return Result.ok(sj.toString());
		} catch (UnsupportedEncodingException ex) {
			return Result.err(ex);
		}
	}
}
