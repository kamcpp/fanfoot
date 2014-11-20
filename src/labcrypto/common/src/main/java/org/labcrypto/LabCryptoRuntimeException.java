package org.labcrypto;

public class LabCryptoRuntimeException extends RuntimeException {
    public LabCryptoRuntimeException() {
    }

    public LabCryptoRuntimeException(String message) {
        super(message);
    }

    public LabCryptoRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public LabCryptoRuntimeException(Throwable cause) {
        super(cause);
    }

    public LabCryptoRuntimeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
