package blazing.types;

import blazing.BlazingLog;
import java.util.function.Supplier;

/**
 *
 * @author hexaredecimal
 * @param <T> The type of the expression stored in result
 * @param <E> The type of the error that is returned
 */
public class Result<T, E> {
	private Result() {} 

	/**
	 * Used to represent a successful result
	 * @param <T>
	 * @param <E>
	 * @param value
	 * @return 
	 */
  public static <T, E> Result<T, E> ok(T value) {
    return new Ok(value);
  }

	/**
	 * Used to represent an error
	 * @param <T>
	 * @param <E>
	 * @param value
	 * @return 
	 */
  public static <T, E> Result<T, E> err(E value) {
    return new Err(value);
  }

	/**
	 * Unwraps the Ok value and panics if there is an error
	 * @return 
	 */
  public T unwrap() {
    if (this instanceof Err err) {
      var error = err.getErr();
      if (error instanceof Throwable v) {
        error = v.getMessage();
      }
      String msg = String.format("Attempt to unwrap value of Result.Err() with error value: `%s`", error);
      BlazingLog.panic(msg);
    }

    var ok = (Ok) this;
    return (T) ok.getValue();
  }

	/**
	 * Unwraps an Ok value and returns the provided value if there is an error
	 * @param value
	 * @return 
	 */
  public T unwrapOr(T value) {
    if (this instanceof Err) {
      return value;
    }
    var ok = (Ok) this;
    return (T) ok.getValue();
  }

	/**
	 * Unwraps an Ok value and calls the supplier method if there is an error
	 * @param fx
	 * @return 
	 */
  public T unwrapOrElse(Supplier<T> fx) {
    if (this instanceof Err) {
      return fx.get();
    }
    var ok = (Ok) this;
    return (T) ok.getValue();
  }

	/**
	 * Unwraps the error and panics if the value is Ok
	 * @return 
	 */
  public E unwrapErr() {
    if (this instanceof Ok) {
      String msg = "Attempt to unwrap value of Result.Ok()";
      BlazingLog.panic(msg);
    }

    var err = (Err) this;
    return (E) err.getErr();
  }

	/**
	 * Checks if the value is an error
	 * @return 
	 */
  public boolean isErr() {
    return this instanceof Err;
  }
  
	/**
	 * Checks if the value is ok
	 * @return 
	 */
  public boolean isOk() {
    return this instanceof Ok;
  }
  
  private static class Ok<T, E> extends Result<T, E> {
    private T value = null;
		private Ok() {}
    private Ok(T value) {
      this.value = value;
    }

    private T getValue() {
      return value;
    }

    @Override
    public String toString() {
      return "Result.Ok(" + value + ")";
    }
  }

  private static class Err<T, E> extends Result<T, E> {
    private T error = null;
		private Err() {}
    private Err(T err) {
      this.error = err;
    }

    private T getErr() {
      return error;
    }

    @Override
    public String toString() {
      return "Result.Err(" + error + ")";
    }
  }
}
