package fittrack.storage;

/**
 * Signals that some given data does not fulfill some constraints.
 */
public class IllegalStorageValueException extends Exception {
    /**
     * @param message should contain relevant information on the failed constraint(s)
     */
    public IllegalStorageValueException(String message) {
        super(message);
    }
}
