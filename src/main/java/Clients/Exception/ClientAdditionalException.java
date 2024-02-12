package Clients.Exception;

public class ClientAdditionalException extends RuntimeException {
    public ClientAdditionalException() {
    }

    public ClientAdditionalException(String message) {
        super(message);
    }

    public ClientAdditionalException(String message, Throwable cause) {
        super(message, cause);
    }

    public ClientAdditionalException(Throwable cause) {
        super(cause);
    }

    public ClientAdditionalException(String message, Throwable cause, boolean enableSuppression,
                                     boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
