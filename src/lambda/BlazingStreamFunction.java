package lambda;

import java.io.File;
import java.io.OutputStream;

/**
 *
 * @author hexaredecimal
 */
public interface BlazingStreamFunction {
	public void stream(OutputStream outstream, File streamfile);
}
