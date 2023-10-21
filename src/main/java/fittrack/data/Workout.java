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

    @Override
    public String toString() {
        return String.format("[W] %s (%s, %s)", name, calories, date);
    }
}
