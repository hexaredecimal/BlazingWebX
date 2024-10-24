package blazing.lambda;

import java.io.File;
import java.io.OutputStream;

/**
 *
 * @author hexaredecimal
 */
public interface BlazingStreamFunction {
	/**
	 * Used as a callback function for when you want to stream a file to the client.
	 * @param outstream
	 * @param streamfile 
	 */
	public void stream(OutputStream outstream, File streamfile);
}
