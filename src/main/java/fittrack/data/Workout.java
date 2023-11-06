package fittrack.data;

public class Workout {
    private final String name;
    private final Calories calories;
    private final Date date;

    public Workout(String name, Calories calories, Date date) {
        assert name != null && calories != null && date != null;

        this.name = name;
        this.calories = calories;
        this.date = date;
    }

    public Calories getCalories() {
        assert calories.value != 0;
        return calories;
    }

    public Date getDate() {
        return this.date;
    }

    public String getName() {
        return name;
    }

    public String formatToFile() {
        return String.format("%s | %s | %s", name, calories, date);
    }

    @Override
    public String toString() {
        return String.format("[W] %s (%s, %s)", name, calories, date);
    }
}
