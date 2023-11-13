package fittrack.data;

import fittrack.parser.ParseException;
import fittrack.parser.PatternMatchFailException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Meal {
    private static final String NAME_GRP = "name";
    private static final String CALORIES_GRP = "calories";
    private static final String DATE_GRP = "date";
    private static final Pattern MEAL_PATTERN = Pattern.compile(
            "(?<" + NAME_GRP + ">.+)\\s+c/(?<" + CALORIES_GRP + ">\\S+)(\\s+d/(?<" + DATE_GRP + ">\\S+))?"
    );

    private final String name;
    private final Calories calories;
    private final Date date;

    public Meal(String name, Calories calories, Date date) {
        assert name != null && calories != null && date != null;

        this.name = name;
        this.calories = calories;
        this.date = date;
    }

    public String formatToFile() {
        return String.format("%s | %s | %s", name, calories, date);
    }

    public Calories getCalories() {
        return calories;
    }

    public Date getDate() {
        return this.date;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return String.format("[M] %s (%s, %s)", name, calories, date);
    }

    public static Meal parseMeal(String s) throws ParseException {
        assert s != null;
        String meal = s.strip();

        final Matcher matcher = MEAL_PATTERN.matcher(meal);
        if (!matcher.matches()) {
            throw new PatternMatchFailException();
        }
        final String name = matcher.group(NAME_GRP);
        final String caloriesData = matcher.group(CALORIES_GRP);
        final String dateData = matcher.group(DATE_GRP);

        Calories calories = Calories.parseCalories(caloriesData);
        Date date;
        if (dateData == null) {
            date = Date.today();
        } else {
            date = Date.parseDate(dateData);
        }
        return new Meal(name, calories, date);
    }
}
