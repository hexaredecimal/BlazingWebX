package blazing.types;

import blazing.BlazingLog;
import java.util.function.Function;
import java.util.function.Supplier;

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

  public T unwrapOr(T value) {
    if (this instanceof Err) {
      return value;
    }
    var ok = (Ok) this;
    return (T) ok.getValue();
  }

  public T unwrapOrElse(Supplier<T> fx) {
    if (this instanceof Err) {
      return fx.get();
    }
    var ok = (Ok) this;
    return (T) ok.getValue();
  }

  public boolean isErr() {
    return this instanceof Err;
  }
  
  public boolean isOk() {
    return this instanceof Ok;
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
}
