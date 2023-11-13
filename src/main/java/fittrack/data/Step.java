package fittrack.data;

import fittrack.parser.NumberFormatException;
import fittrack.parser.ParseException;
import fittrack.parser.PatternMatchFailException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Step {

    private static final String STEP_CG = "step";
    private static final String DATE_CG = "date";
    private static final Pattern STEP_PATTERN = Pattern.compile(
        "(?<" + STEP_CG + ">.+)(\\s+d/(?<" + DATE_CG + ">\\S+))?"
    );
    private final int steps;
    private final Date date;

    public Step(int steps, Date date) {
        assert steps >= 0 && date != null;
        this.steps = steps;
        this.date = date;
    }

    public static Step parseStep(String s) throws ParseException {
        assert s != null;
        String steps = s.strip();

        final Matcher matcher = STEP_PATTERN.matcher(steps);
        if (!matcher.matches()) {
            throw new PatternMatchFailException();
        }

        final String stepData = matcher.group(STEP_CG);
        final String dateData = matcher.group(DATE_CG);

        try {
            int step = Integer.parseInt(stepData);
            if (step <= 0) {
                throw new NumberFormatException("Steps must be a positive integer.");
            }
            Date date;
            if (dateData == null) {
                date = Date.today();
            } else {
                date = Date.parseDate(dateData);
            }
            return new Step(step, date);
        } catch (java.lang.NumberFormatException e) {
            throw new NumberFormatException("Steps must be a positive integer.");
        }
    }

    public String formatToFile() {
        return String.format("%s | %s", steps, date);
    }

    public int getSteps() {
        return steps;
    }

    public Date getDate() {
        return this.date;
    }

    public Step sum(Step another) {
        return new Step(this.steps + another.steps, this.date);
    }
    @Override
    public String toString() {
        return String.format("[S] %s steps (%s)", steps, date);
    }
}
