package fittrack.data;

import java.util.Objects;

public class Weight {
    public double value;

    public Weight(double weight) {
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
        return value + "kg";
    }
}
