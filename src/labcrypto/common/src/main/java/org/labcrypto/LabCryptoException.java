package org.labcrypto;

public class LabCryptoException extends Exception {
    public LabCryptoException() {
    }

    public LabCryptoException(String message) {
        super(message);
    }

    public LabCryptoException(String message, Throwable cause) {
        super(message, cause);
    }

    public LabCryptoException(Throwable cause) {
        super(cause);
    }

    public LabCryptoException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
