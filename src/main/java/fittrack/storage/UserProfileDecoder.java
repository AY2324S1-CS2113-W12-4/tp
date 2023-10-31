package fittrack.storage;

import fittrack.UserProfile;
import fittrack.data.Calories;
import fittrack.data.Gender;
import fittrack.data.Height;
import fittrack.data.Weight;
import fittrack.parser.IllegalValueException;
import fittrack.storage.Storage.StorageOperationException;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserProfileDecoder {
    private static final Pattern HEIGHT_PATTERN = Pattern.compile(
            "Height:\\s+(?<height>\\S+)cm"
    );
    private static final Pattern WEIGHT_PATTERN = Pattern.compile(
            "Weight:\\s+(?<weight>\\S+)kg"
    );
    private static final Pattern GENDER_PATTERN = Pattern.compile(
            "Gender:\\s+(?<gender>\\S+)"
    );
    private static final Pattern CALORIES_PATTERN = Pattern.compile(
                    "Daily calorie limit: (?<calLimit>\\S+)kcal"
    );

    /**
     * Decodes {@code encodedUserProfile} into a {@code UserProile} containing the decoded data.
     *
     * @throws IllegalValueException if any of the fields in any encoded person string is invalid.
     * @throws StorageOperationException if the {@code encodedUserProfile} is in an invalid format.
     */
    public static UserProfile decodeUserProfile(List<String> encodedUserProfile)
            throws IllegalValueException, StorageOperationException {
        String[] decodedUserProfile = new String[4];
        for (int i = 0; i < encodedUserProfile.size(); i++) {
            decodedUserProfile[i] = encodedUserProfile.get(i);
        }
        final Matcher heightMatcher = HEIGHT_PATTERN.matcher(decodedUserProfile[0]);
        final Matcher weightMatcher = WEIGHT_PATTERN.matcher(decodedUserProfile[1]);
        final Matcher caloriesMatcher = CALORIES_PATTERN.matcher(decodedUserProfile[2]);
        final Matcher genderMatcher = GENDER_PATTERN.matcher(decodedUserProfile[3]);

        if (!heightMatcher.matches() || !weightMatcher.matches()
                || !caloriesMatcher.matches()) {
            throw new StorageOperationException("File containing profile has invalid format. " +
                    "Please delete the file and run the program again");
        }

        final double height = Double.parseDouble(heightMatcher.group("height"));
        final double weight = Double.parseDouble(weightMatcher.group("weight"));
        final double dailyCalorieLimit = Double.parseDouble(caloriesMatcher.group("calLimit"));
        final char gender = genderMatcher.group("gender").charAt(0);

        Height heightData = new Height(height);
        Weight weightData = new Weight(weight);
        Calories caloriesData = new Calories(dailyCalorieLimit);
        Gender genderData = new Gender(gender);

        return new UserProfile(heightData, weightData, caloriesData, genderData);
    }
}
