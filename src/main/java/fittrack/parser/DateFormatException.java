package fittrack.parser;

public class DateFormatException extends ParseException {
    public DateFormatException() {
        super("The date format is not valid.");
    }
}
