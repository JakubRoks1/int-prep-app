package demo.exceptions;

public class PasswordChangeException extends RuntimeException {

    public PasswordChangeException(String message) {
        super(message);
    }
}
