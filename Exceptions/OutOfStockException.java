package Exceptions;

/**
 * Exception thrown when a requested item is out of stock in the vending machine.
 */
public class OutOfStockException extends RuntimeException {

    /**
     * Constructs a new OutOfStockException with the specified detail message.
     *
     * @param message the detail message for this exception
     */
    public OutOfStockException(String message) {
        super(message);
    }
}
