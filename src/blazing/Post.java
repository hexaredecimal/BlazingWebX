package blazing;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation used to mark methods to be used for responding to post requests from the client.
 * 
 * <pre>
 * {@code
 * 	@Post("/users")
 * 	public static void users(BlazingResponse response) {
 * 		...
 * 	}
 * }
 * </pre>
 * 
 * The above snipped declares a method and marks it as the respondent for post request made to the the path `/users`.
 * The default path for a Post is `/`, which represent "home" or localhost. 
 * 
 * @author hexaredecimal
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Post {

	String value() default "/";
}
