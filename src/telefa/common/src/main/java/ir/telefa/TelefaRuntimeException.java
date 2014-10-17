package ir.telefa;

public class TelefaRuntimeException extends RuntimeException {
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
