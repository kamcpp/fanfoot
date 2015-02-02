package ir.fanfoot;

import org.labcrypto.LabCryptoRuntimeException;

public class FanFootRuntimeException extends LabCryptoRuntimeException {

    public FanFootRuntimeException() {
    }

    public FanFootRuntimeException(String message) {
        super(message);
    }

    public FanFootRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public FanFootRuntimeException(Throwable cause) {
        super(cause);
    }

    public FanFootRuntimeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
