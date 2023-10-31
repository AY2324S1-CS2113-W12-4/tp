package fittrack;

import fittrack.data.*;

public class UserProfile {
    private Height height;
    private Weight weight;
    private Calories dailyCalorieLimit;
    private Bmi bmi;
    private Gender gender;

    public UserProfile() {
        this(new Height(1), new Weight(1), new Calories(0), new Gender('M'));
    }

    public UserProfile(Height height, Weight weight, Calories dailyCalorieLimit, Gender gender) {
        this.height = height;
        this.weight = weight;
        this.dailyCalorieLimit = dailyCalorieLimit;
        this.gender = gender;
        updateBmi();
    }

    public Height getHeight() {
        return height;
    }

    public void setHeight(Height height) {
        this.height = height;
        updateBmi();
    }

    public Weight getWeight() {
        return weight;
    }

    public void setWeight(Weight weight) {
        this.weight = weight;
        updateBmi();
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Calories getDailyCalorieLimit() {
        return dailyCalorieLimit;
    }

    public void setDailyCalorieLimit(Calories dailyCalorieLimit) {
        this.dailyCalorieLimit = dailyCalorieLimit;
    }

    public Bmi getBmi() {
        return bmi;
    }

    public String getBmiCategory() {
        return bmi.getCategory();
    }

    private void updateBmi() {
        this.bmi = new Bmi(height, weight);
    }

    public String toString() {
        return "Height: " + height.toString() + "\n" +
                "Weight: " + weight.toString() + "\n" +
                "Daily calorie limit: " + dailyCalorieLimit.toString() + "\n" +
                "BMI: " + bmi.toString() + "\n" +
                "Gender: " + gender.toString();
    }
}
