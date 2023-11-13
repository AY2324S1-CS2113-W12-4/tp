package fittrack.parser;

public class IndexOutOfBoundsException extends IllegalValueException {
    public static final IndexOutOfBoundsException LIST_EMPTY = new IndexOutOfBoundsException(
            "Index out of range. List is empty!"
    );
    public static final IndexOutOfBoundsException INDEX_INVALID = new IndexOutOfBoundsException(
            "Index out of range. Index must be in the range of 1 and the list size!"
    );

    public IndexOutOfBoundsException(String message) {
        super(message);
    }
}
