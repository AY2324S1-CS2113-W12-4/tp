package fittrack.data;

import fittrack.parser.IllegalValueException;
import fittrack.parser.NumberFormatException;

import java.util.Objects;

public class Height {
    public static final double MAX_VALUE = 1000;

    public final double value;

    public Height(double height) {
        assert height > 0 && height <= MAX_VALUE;
        this.value = height;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Height height1 = (Height) o;
        return Double.compare(value, height1.value) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return String.format("%.1fcm", value);
    }

    public double calculateIdealWeight(){
        return 50 + (0.91 * (value - 152.4));
    }

    public static Height parseHeight(String s) throws IllegalValueException {
        assert s != null;
        String heightData = s.strip();

        try {
            double height = Double.parseDouble(heightData);
            if (height <= 0) {
                throw new IllegalValueException("Height must be a positive value.");
            } else if (height > MAX_VALUE) {
                throw new IllegalValueException("Height value is too large.");
            }
            return new Height(height);
        } catch (java.lang.NumberFormatException e) {
            throw new NumberFormatException("Height must be a number.");
        }
    }
}
