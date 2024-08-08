package jedi;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * This annotation is used to mark classes that represent servers. 
 * <pre>
 * {@code
 * 	@WebServer("6900")
 * 	public class WebServer {
 * 		...
 * 	}
 * }
 * </pre>
 * 
 * The above snipped declares a class and marks it as a webserver, meaning it contains methods for responding to https requests. 
 * The port is specified in the parentheses and `8080` is the default port.  
 * 
 * @author hexaredecimal
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface WebServer {
	String value() default "8080";
}
