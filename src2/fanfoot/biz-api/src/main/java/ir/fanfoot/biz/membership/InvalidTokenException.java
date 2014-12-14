package ir.fanfoot.biz.membership;

import ir.fanfoot.FanFootException;

public class InvalidTokenException extends FanFootException {
    public InvalidTokenException() {
    }

    public InvalidTokenException(String message) {
        super(message);
    }

    public InvalidTokenException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidTokenException(Throwable cause) {
        super(cause);
    }

    public InvalidTokenException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
