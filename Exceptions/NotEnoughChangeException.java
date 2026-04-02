package Exceptions;

/**
 * Exception thrown when the vending machine cannot provide the exact change
 * for a transaction.
 */
public class NotEnoughChangeException extends RuntimeException {

    /**
     * Constructs a new NotEnoughChangeException with the specified detail message.
     *
     * @param message the detail message for this exception
     */
    public NotEnoughChangeException(String message) {

        super(message);
    }
}
