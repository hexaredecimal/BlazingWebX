package blazing.types;

import blazing.BlazingLog;

/**
 *
 * @author hexaredecimal
 * @param <T> The type of the expression stored in result
 * @param <E> The type of the error that is returned
 */
public class Result<T, E> {

  public static <T, E> Result<T, E> ok(T value) {
    return new Ok(value);
  }

  public static <T, E> Result<T, E> err(E value) {
    return new Err(value);
  }

  public T unwrap() {
    if (this instanceof Err err) {
      String msg = String.format("Attempt to unwrap value of %s with error value: `%s`", this, err.getErr());
      BlazingLog.panic(msg);
    } 
    
    var ok = (Ok) this;
    return (T) ok.getValue(); 
  }

  private static class Ok<T, E> extends Result<T, E> {

    private final T value;

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

    private final T err;

    private Err(T err) {
      this.err = err;
    }

    private T getErr() {
      return err;
    }

    @Override
    public String toString() {
      return "Result.Err(" + err + ")";
    }
  }

  public static Result<Double, String> safeDiv(double x, double y) {
    if (y == 0) {
      return Result.err("Divide by zero");
    }

    return Result.ok(x / y);
  }
}
