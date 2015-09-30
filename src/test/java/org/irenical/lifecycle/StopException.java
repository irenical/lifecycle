package org.irenical.lifecycle;

public class StopException extends Exception {

    private static final long serialVersionUID = 1L;

    public StopException() {
        super();
    }

    public StopException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public StopException(String message, Throwable cause) {
        super(message, cause);
    }

    public StopException(String message) {
        super(message);
    }

    public StopException(Throwable cause) {
        super(cause);
    }

}
