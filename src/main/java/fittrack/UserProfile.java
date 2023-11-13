package fittrack;

import fittrack.data.Gender;
import fittrack.data.Weight;
import fittrack.data.Height;
import fittrack.data.Calories;
import fittrack.data.Bmi;
import fittrack.parser.ParseException;
import fittrack.parser.PatternMatchFailException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserProfile {
    private static final String HEIGHT_GRP = "height";
    private static final String WEIGHT_GRP = "weight";
    private static final String GENDER_GRP = "gender";
    private static final String CAL_LIMIT_GRP = "calLimit";
    private static final Pattern USER_PROFILE_PATTERN = Pattern.compile(
            "h/(?<" + HEIGHT_GRP + ">\\S+)\\s+w/(?<" + WEIGHT_GRP + ">\\S+)\\s+" +
                    "g/(?<" + GENDER_GRP + ">\\S+)\\s+l/(?<" + CAL_LIMIT_GRP + ">\\S+)"
    );

    private Height height;
    private Weight weight;
    private Gender gender;
    private Calories dailyCalorieLimit;
    private Bmi bmi;

    public UserProfile() {
        this(new Height(1), new Weight(1), new Calories(0), Gender.MALE);
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
                "Gender: " + gender.toString() + "\n" +
                "Daily calorie limit: " + dailyCalorieLimit.toString() + "\n" +
                "BMI: " + bmi.toString();
    }

    public static UserProfile parseUserProfile(String s) throws ParseException {
        assert s != null;
        String profile = s.strip();

        final Matcher matcher = USER_PROFILE_PATTERN.matcher(profile);
        if (!matcher.matches()) {
            throw new PatternMatchFailException();
        }
        final String heightData = matcher.group(HEIGHT_GRP);
        final String weightData = matcher.group(WEIGHT_GRP);
        final String dailyCalorieLimitData = matcher.group(CAL_LIMIT_GRP);
        final String genderData = matcher.group(GENDER_GRP);

        Height height = Height.parseHeight(heightData);
        Weight weight = Weight.parseWeight(weightData);
        Calories dailyCalorieLimit = Calories.parseCalories(dailyCalorieLimitData);
        Gender gender = Gender.parseGender(genderData);
        return new UserProfile(height, weight, dailyCalorieLimit, gender);
    }
}
