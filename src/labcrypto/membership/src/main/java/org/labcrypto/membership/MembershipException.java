package org.labcrypto.membership;


import org.labcrypto.LabCryptoException;

public class MembershipException extends LabCryptoException {
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
