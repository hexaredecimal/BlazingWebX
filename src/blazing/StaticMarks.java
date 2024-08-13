package blazing;

import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * This annotation is used to mark classes that represent static content. 
 * <pre>
 * {@code
 * 	@Static("images")
 * 	public class WebServer {
 * 		...
 * 	}
 * }
 * </pre>
 * 
 * @author hexaredecimal
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface StaticMarks {
	Static[] value();
}
