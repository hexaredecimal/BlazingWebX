package blazing;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation used to mark methods to be used for initializing the server resources.
 * 
 * <pre>
 * {@code
 * 	@Initializer("/users")
 * 	public static void init() {
 * 		...
 * 	}
 * }
 * </pre>
 * 
 * @author hexaredecimal
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Initializer {
}
