package ir.fanfoot.biz.membership;

import ir.fanfoot.FanFootException;

public class TokenNotFoundException extends FanFootException {
    public TokenNotFoundException() {
    }

    public TokenNotFoundException(String message) {
        super(message);
    }

    public TokenNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public TokenNotFoundException(Throwable cause) {
        super(cause);
    }

    public TokenNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
