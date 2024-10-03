package Clients.Exception;

public class NotFoundException extends RuntimeException{

    public NotFoundException(final String object, String key, String value) {
        super(String.format("%s not found by %s = %s", object, key, value));
    }

    public NotFoundException(final String message) {
        super(message);
    }
}
