package org.irenical.lifecycle;

public class IsRunningException extends Exception {

  private static final long serialVersionUID = 1L;

  public IsRunningException() {
    super();
  }

  public IsRunningException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }

  public IsRunningException(String message, Throwable cause) {
    super(message, cause);
  }

  public IsRunningException(String message) {
    super(message);
  }

  public IsRunningException(Throwable cause) {
    super(cause);
  }

}
