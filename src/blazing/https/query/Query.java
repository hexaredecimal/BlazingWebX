package blazing.https.query;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;
import java.util.StringJoiner;

/**
 *
 * @author ERC
 */
public class Query {

    public static String from(Map<?, ?> args) throws UnsupportedEncodingException {
        StringJoiner sj = new StringJoiner("&");
        for (Map.Entry<?, ?> entry : args.entrySet()) {
            sj.add(URLEncoder.encode(entry.getKey().toString(), "UTF-8") + "="
                    + URLEncoder.encode(entry.getValue().toString(), "UTF-8"));
        }
        
        return sj.toString();
    }
}
