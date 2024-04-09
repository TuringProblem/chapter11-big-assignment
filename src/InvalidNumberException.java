package src;

import java.security.MessageDigest;
import java.util.NoSuchElementException;

public class InvalidNumberException extends NoSuchElementException {
    @SuppressWarnings("unused")
    public InvalidNumberException() {
        super();
    }
    public InvalidNumberException(String message) {
        super(message);
    }
}
