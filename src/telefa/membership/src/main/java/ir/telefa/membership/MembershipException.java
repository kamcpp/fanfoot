package ir.telefa.membership;

import ir.telefa.TelefaException;

public class MembershipException extends TelefaException {
    public MembershipException() {
    }

    public MembershipException(String message) {
        super(message);
    }

    public MembershipException(Throwable cause) {
        super(cause);
    }

    public MembershipException(String message, Throwable cause) {
        super(message, cause);
    }
}
