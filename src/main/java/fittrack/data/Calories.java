package fittrack.data;

import java.util.Objects;

public class Calories {
    public double value;

    public Calories(double calories) {
        this.value = calories;
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
        return value + "kcal";
    }
}
