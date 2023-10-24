package fittrack.data;

import java.text.DecimalFormat;
import java.util.Objects;
import java.util.Map;

public class Bmi {

    public double value;
    private final DecimalFormat df = new DecimalFormat("0.00");

    public Bmi(double bmi) {
        this.value = bmi;
    }

    private Map<String, String> createBMICategories() {
        return Map.of(
                "Underweight", "0.0 18.5",
                "Normal weight", "18.5 24.9",
                "Overweight", "25.0 29.9",
                "Obese", "30.0 100.0"
        );
    }

    /**
     * Finds the category that the user's bmi belongs to.
     *
     * @return bmi category
     */
    public String getCategory() {
        // Create a Map to store BMI classifications
        Map<String, String> bmiCategory= createBMICategories();

        // Determine the BMI classification
        for (Map.Entry<String, String> entry : bmiCategory.entrySet()) {
            String range = entry.getValue();
            String[] rangeBounds = range.split(" ");
            double lowerBound = Double.parseDouble(rangeBounds[0]);
            double upperBound = Double.parseDouble(rangeBounds[rangeBounds.length - 1]);
            if (value >= lowerBound && value <= upperBound) {
                return entry.getKey();
            }
        }
        return "Unknown Category";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Bmi bmi1 = (Bmi) o;
        return Double.compare(value, bmi1.value) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return df.format(value);
    }
}