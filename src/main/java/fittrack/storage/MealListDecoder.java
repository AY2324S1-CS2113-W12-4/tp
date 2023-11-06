package fittrack.storage;

import fittrack.MealList;
import fittrack.data.Calories;
import fittrack.data.Meal;
import fittrack.data.Date;
import fittrack.parser.IllegalValueException;
import fittrack.storage.Storage.StorageOperationException;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MealListDecoder {

    private static final Pattern MEAL_PATTERN = Pattern.compile(
            "(?<name>\\S+)\\s*\\|\\s*(?<calories>\\S+)kcal\\s*\\|\\s*(?<date>\\S+)"
    );

    /**
     * Decodes {@code encodedMealList} into a {@code MealList} containing the decoded data.
     *
     * @throws IllegalValueException if any of the fields in any encoded person string is invalid.
     * @throws StorageOperationException if the {@code encodedMealList} is in an invalid format.
     */
    public static MealList decodeMealList(List<String> encodedMealList)
            throws IllegalValueException, StorageOperationException {
        MealList mealList = new MealList();
        for (String encodedMeal : encodedMealList) {
            mealList.addToList(decodeMealsFromString(encodedMeal));
        }
        return mealList;
    }

    /**
     * Decodes {@code encodedMeal} into a {@code Meal}.
     *
     * @throws StorageOperationException if {@code encodedPerson} is in an invalid format.
     */
    public static Meal decodeMealsFromString(String encodedMeal)
            throws StorageOperationException {
        final Matcher matcher = MEAL_PATTERN.matcher(encodedMeal);
        if (!matcher.matches()) {
            throw new StorageOperationException("File containing meals has invalid format. " +
                    "Please delete the file and run the program again");
        }

        final String name = matcher.group("name");
        final String calories = matcher.group("calories");
        final String date = matcher.group("date");

        double caloriesInDouble = Double.parseDouble(calories);

        return new Meal(name, new Calories(caloriesInDouble), new Date(date));
    }
}
