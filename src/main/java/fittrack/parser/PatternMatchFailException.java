package fittrack.parser;

public class PatternMatchFailException extends ParseException {

    public PatternMatchFailException() {
        super("The input pattern is not valid.");
    }
}
