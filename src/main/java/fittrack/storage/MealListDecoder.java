package fittrack.storage;

import fittrack.MealList;
import fittrack.Ui;
import fittrack.data.Calories;
import fittrack.data.Meal;
import fittrack.data.Date;
import fittrack.parser.DateFormatException;
import fittrack.parser.IllegalValueException;
import fittrack.storage.Storage.StorageOperationException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// @@author J0shuaLeong
public class MealListDecoder {
    private static final Pattern MEAL_PATTERN = Pattern.compile(
            "(?<name>\\S+)\\s*\\|\\s*(?<calories>\\S+)kcal\\s*\\|\\s*(?<date>\\S+)"
    );
    private static final StorageOperationException CONTENT_CORRUPTION_EXCEPTION = new StorageOperationException(
            "Creating new meal list file..."
    );
    private static final String FILE_NAME = "mealList.txt";

    /**
     * Decodes {@code encodedMealList} into a {@code MealList} containing the decoded data.
     *
     * @throws StorageOperationException if the {@code encodedMealList} is in an invalid format.
     */
    public static MealList decodeMealList(List<String> encodedMealList, Path mealListPath)
            throws StorageOperationException, IOException {
        MealList mealList = new MealList();
        for (String encodedMeal : encodedMealList) {
            mealList.addToList(decodeMealsFromString(encodedMeal, mealListPath));
        }
        return mealList;
    }

    /**
     * Decodes {@code encodedMeal} into a {@code Meal}.
     *
     * @throws StorageOperationException if {@code encodedPerson} is in an invalid format.
     */
    public static Meal decodeMealsFromString(String encodedMeal, Path mealListPath)
            throws StorageOperationException, IOException {
        final Matcher matcher = MEAL_PATTERN.matcher(encodedMeal);
        if (!matcher.matches()) {
            handleCorruptedFile(mealListPath);
            throw CONTENT_CORRUPTION_EXCEPTION;
        }

        final String name = matcher.group("name");
        final String caloriesData = matcher.group("calories");
        final String dateData = matcher.group("date");

        try {
            Calories calories = Calories.parseCalories(caloriesData);
            Date date = Date.parseDate(dateData);
            return new Meal(name, calories, date);
        } catch (IllegalValueException | DateFormatException e) {
            handleCorruptedFile(mealListPath);
            throw CONTENT_CORRUPTION_EXCEPTION;
        }
    }

    public static void handleCorruptedFile(Path filePath) throws IOException {
        String newFileContent = "";
        Ui.printPromptForCreateNewFile(FILE_NAME);
        if (Ui.createNewFile()) {
            Files.write(filePath, newFileContent.getBytes());
        } else {
            throw new Ui.ForceExitException();
        }
    }
}
// @@author
