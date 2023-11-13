package fittrack.data;

import java.util.Objects;
import java.util.Map;

// @@author J0shuaLeong
public class Bmi {

    public final double value;


    // Method for test
    Bmi(double bmi) {
        this.value = bmi;
    }

    public Bmi(Height height, Weight weight) {
        assert (height != null && height.value > 0 && weight != null);
        double heightInMetres = height.value / 100;
        this.value = weight.value / heightInMetres / heightInMetres;
    }

    private Map<String, String> createBMICategories() {
        return Map.of(
                "Underweight", "0.0 18.5",
                "Normal weight", "18.5 25.0",
                "Overweight", "25.0 30.0",
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
            if (value >= lowerBound && value < upperBound) {
                return entry.getKey();
            }
        }
        return "Obese";
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
        return String.format("%.2f", value);
    }
}
// @@author
