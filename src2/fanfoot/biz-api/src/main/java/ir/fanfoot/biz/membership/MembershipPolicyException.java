package ir.fanfoot.biz.membership;

import ir.fanfoot.FanFootException;

public class MembershipPolicyException extends FanFootException {
    public MembershipPolicyException() {
    }

    public MembershipPolicyException(String message) {
        super(message);
    }

    public MembershipPolicyException(String message, Throwable cause) {
        super(message, cause);
    }

    public MembershipPolicyException(Throwable cause) {
        super(cause);
    }

    public MembershipPolicyException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
