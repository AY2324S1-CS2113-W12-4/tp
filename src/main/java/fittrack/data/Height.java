package fittrack.data;

import java.util.Objects;

public class Height {
    public double value;

    public Height(double height) {
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
        return value + "cm";
    }
}
