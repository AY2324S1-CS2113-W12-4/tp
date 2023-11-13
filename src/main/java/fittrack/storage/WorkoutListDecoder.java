package fittrack.storage;

import fittrack.Ui;
import fittrack.WorkoutList;
import fittrack.data.Calories;
import fittrack.data.Date;
import fittrack.data.Workout;
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
public class WorkoutListDecoder {

    private static final String NAME_GRP = "name";
    private static final String CALORIES_GRP = "calories";
    private static final String DATE_GRP = "date";
    private static final Pattern WORKOUT_PATTERN = Pattern.compile(
            "(?<" + NAME_GRP + ">.+)\\s+\\|\\s*(?<" + CALORIES_GRP + ">\\S+)kcal\\s+\\|\\s+(?<" + DATE_GRP + ">\\S+)?"
    );
    private static final StorageOperationException CONTENT_CORRUPTION_EXCEPTION = new StorageOperationException(
            "Creating new workout list file..."
    );
    private static final String FILE_NAME = "workoutList.txt";

    /**
     * Decodes {@code encodedWorkoutList} into a {@code WorkoutList} containing the decoded data.
     *
     * @throws Storage.StorageOperationException if the {@code encodedWorkoutList} is in an invalid format.
     */
    public static WorkoutList decodeWorkoutList(List<String> encodedWorkoutList, Path workoutListPath)
            throws StorageOperationException, IOException {
        WorkoutList workoutList = new WorkoutList();
        for (String encodedWorkout : encodedWorkoutList) {
            workoutList.addToList(decodeWorkoutFromString(encodedWorkout, workoutListPath));
        }
        return workoutList;
    }

    /**
     * Decodes {@code encodedWorkout} into a {@code Workout}.
     *
     * @throws Storage.StorageOperationException if {@code encodedWorkout} is in an invalid format.
     */
    public static Workout decodeWorkoutFromString(String encodedWorkout, Path workoutListPath)
            throws StorageOperationException, IOException {
        final Matcher matcher = WORKOUT_PATTERN.matcher(encodedWorkout);
        if (!matcher.matches()) {
            handleCorruptedFile(workoutListPath);
            throw CONTENT_CORRUPTION_EXCEPTION;
        }

        final String name = matcher.group("name");
        final String caloriesData = matcher.group("calories");
        final String dateData = matcher.group("date");

        try {
            Calories calories = Calories.parseCalories(caloriesData);
            Date date = Date.parseDate(dateData);
            return new Workout(name, calories, date);
        } catch (IllegalValueException | DateFormatException e) {
            handleCorruptedFile(workoutListPath);
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
