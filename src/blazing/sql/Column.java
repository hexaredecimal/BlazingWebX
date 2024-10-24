package blazing.sql;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Used to annotate fields that match to SQL columns.
 * @author hexaredecimal
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface Column {

	String name();
}
