package ir.telefa;

public class TelefaException extends Exception {
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
