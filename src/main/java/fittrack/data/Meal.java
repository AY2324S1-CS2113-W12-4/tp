package fittrack.data;

public class Meal {
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
        return String.format("%s | %s| %s", name, calories, date);
    }

    public Calories getCalories() {
        return calories;
    }

    public Date getMealDate() {
        return this.date;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return String.format("[M] %s (%s, %s)", name, calories, date);
    }
}
