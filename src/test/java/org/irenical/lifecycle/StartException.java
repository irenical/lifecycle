package org.irenical.lifecycle;

public class StartException extends Exception {

    private static final long serialVersionUID = 1L;

    public StartException() {
        super();
    }

    public StartException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public StartException(String message, Throwable cause) {
        super(message, cause);
    }

    public StartException(String message) {
        super(message);
    }

    public StartException(Throwable cause) {
        super(cause);
    }

}
