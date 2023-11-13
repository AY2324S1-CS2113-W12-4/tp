package fittrack.data;

import fittrack.parser.IllegalValueException;
import fittrack.parser.NumberFormatException;

import java.util.Objects;

public class Weight {
    public static final double MAX_VALUE = 1000;

    public final double value;

    public Weight(double weight) {
        assert weight > 0 && weight <= MAX_VALUE;
        this.value = weight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Weight weight1 = (Weight) o;
        return Double.compare(value, weight1.value) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return String.format("%.1fkg", value);
    }

    public static Weight parseWeight(String s) throws IllegalValueException {
        assert s != null;
        String weightData = s.strip();

        try {
            double weight = Double.parseDouble(weightData);
            if (weight <= 0) {
                throw new IllegalValueException("Weight must be a positive value.");
            } else if (weight > MAX_VALUE) {
                throw new IllegalValueException("Weight value is too large.");
            }
            return new Weight(weight);
        } catch (java.lang.NumberFormatException e) {
            throw new NumberFormatException("Weight must be a number.");
        }
    }
}
