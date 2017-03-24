package dao;

public class Option<F, S> {
  private F object;
  private S error;
  
  public Option(F object, S error) {
    this.object = object;
    this.error = error;
  }

  public F getObject() {
    return object;
  }

  public S getError() {
    return error;
  }

  public boolean isOk() {
    return (error == null);
  }

  public boolean hasError() {
    return !isOk();
  }

  public static <T, E> Option<T, E> error(E error) {
    return new Option<>(null, error);
  }

  public static <T, E> Option<T, E> success(T result) {
    return new Option<>(result, null);
  }

  public static <T, E> Option<T, E> success() {
    return new Option<>(null, null);
  }
}
