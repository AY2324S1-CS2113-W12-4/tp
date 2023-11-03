package fittrack.storage;

import fittrack.WorkoutList;
import fittrack.data.Calories;
import fittrack.data.Date;
import fittrack.data.Workout;
import fittrack.parser.IllegalValueException;
import fittrack.storage.Storage.StorageOperationException;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WorkoutListDecoder {

    private static final Pattern WORKOUT_PATTERN = Pattern.compile(
            "(?<name>\\S+)\\s*\\|\\s*(?<calories>\\S+)kcal\\s*\\|\\s*(?<date>\\S+)" // add \\d+\\.\\d+ if got decimal
    );

    /**
     * Decodes {@code encodedWorkoutList} into a {@code WorkoutList} containing the decoded data.
     *
     * @throws IllegalValueException if any of the fields in any encoded person string is invalid.
     * @throws Storage.StorageOperationException if the {@code encodedWorkoutList} is in an invalid format.
     */
    public static WorkoutList decodeWorkoutList(List<String> encodedWorkoutList)
            throws IllegalValueException, StorageOperationException {
        WorkoutList workoutList = new WorkoutList();
        for (String encodedWorkout : encodedWorkoutList) {
            workoutList.addToList(decodeWorkoutFromString(encodedWorkout));
        }
        return workoutList;
    }

    /**
     * Decodes {@code encodedWorkout} into a {@code Workout}.
     *
     * @throws Storage.StorageOperationException if {@code encodedWorkout} is in an invalid format.
     */
    public static Workout decodeWorkoutFromString(String encodedWorkout)
            throws StorageOperationException {
        final Matcher matcher = WORKOUT_PATTERN.matcher(encodedWorkout);
        if (!matcher.matches()) {
            throw new Storage.StorageOperationException("File containing workouts has invalid format. " +
                    "Please delete the file and run the program again");
        }

        final String name = matcher.group("name");
        final String calories = matcher.group("calories");
        final String date = matcher.group("date");

        double caloriesInDouble = Double.parseDouble(calories);

        return new Workout(name, new Calories(caloriesInDouble), new Date(date));
    }
}
