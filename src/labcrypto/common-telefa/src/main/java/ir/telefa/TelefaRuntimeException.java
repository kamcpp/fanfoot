package ir.telefa;

import org.labcrypto.LabCryptoRuntimeException;

public class TelefaRuntimeException extends LabCryptoRuntimeException {
    public TelefaRuntimeException() {
    }

    public TelefaRuntimeException(String message) {
        super(message);
    }

    public TelefaRuntimeException(Throwable cause) {
        super(cause);
    }

    public TelefaRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }
}
