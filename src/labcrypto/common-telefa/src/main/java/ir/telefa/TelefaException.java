package ir.telefa;

import org.labcrypto.LabCryptoException;

public class TelefaException extends LabCryptoException {
    public TelefaException() {
    }

    public TelefaException(String message) {
        super(message);
    }

    public TelefaException(Throwable cause) {
        super(cause);
    }

    public TelefaException(String message, Throwable cause) {
        super(message, cause);
    }
}
