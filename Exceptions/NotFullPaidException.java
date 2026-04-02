package Exceptions;

/**
 * Exception thrown when a transaction cannot be completed due to insufficient payment.
 */
public class NotFullPaidException extends RuntimeException {

    /**
     * Constructs a new NotFullPaidException with the specified detail message.
     *
     * @param message the detail message for this exception
     */
    public NotFullPaidException(String message) {
        super(message);
    }
}
