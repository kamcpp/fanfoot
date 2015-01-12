package ir.fanfoot.biz.membership;

import ir.fanfoot.FanFootException;

public class InvalidPrincipleException extends FanFootException {
    public InvalidPrincipleException() {
    }

    public InvalidPrincipleException(String message) {
        super(message);
    }

    public InvalidPrincipleException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidPrincipleException(Throwable cause) {
        super(cause);
    }

    public InvalidPrincipleException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
