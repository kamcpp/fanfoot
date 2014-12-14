package ir.fanfoot;

public class FanFootException extends Exception {
    public FanFootException() {
    }

    public FanFootException(String message) {
        super(message);
    }

    public FanFootException(String message, Throwable cause) {
        super(message, cause);
    }

    public FanFootException(Throwable cause) {
        super(cause);
    }

    public FanFootException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
