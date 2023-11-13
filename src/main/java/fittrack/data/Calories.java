package fittrack.data;

import fittrack.parser.IllegalValueException;
import fittrack.parser.NumberFormatException;

import java.util.Objects;

public class Calories {
    public static final double MAX_VALUE = 1e6;

    public final double value;

    public Calories(double calories) {
        assert calories >= 0 && calories <= MAX_VALUE;
        this.value = calories;
    }

    public Calories sum(Calories another) {
        return new Calories(this.value + another.value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Calories calories1 = (Calories) o;
        return Double.compare(value, calories1.value) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return String.format("%.0fkcal", value);
    }

    public static Calories parseCalories(String s) throws IllegalValueException {
        assert s != null;
        String caloriesData = s.strip();

        try {
            double calories = Double.parseDouble(caloriesData);
            if (calories < 0) {
                throw new IllegalValueException("Calories must not be a negative value.");
            } else if (calories > MAX_VALUE) {
                throw new IllegalValueException("Calories value is too large.");
            }
            return new Calories(calories);
        } catch (java.lang.NumberFormatException e) {
            throw new NumberFormatException("Calorie must be a number.");
        }
    }
}
