package fittrack.storage;

import fittrack.Ui;
import fittrack.UserProfile;
import fittrack.MealList;
import fittrack.StepList;
import fittrack.WorkoutList;
import fittrack.data.Meal;
import fittrack.data.Step;
import fittrack.data.Workout;
import fittrack.parser.IllegalValueException;

import java.io.FileNotFoundException;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

// @@author J0shuaLeong
public class Storage {

    private static final String FILE_DIRECTORY = "data";
    private static final String PROFILE_FILE_PATH = "./data/profile.txt";
    private static final String MEAL_LIST_FILE_PATH = "./data/mealList.txt";
    private static final String WORKOUT_LIST_FILE_PATH = "./data/workoutList.txt";
    private static final String STEP_LIST_FILE_PATH = "./data/stepList.txt";
    private static final String FILE_TYPE = ".txt";
    private Ui ui = new Ui();

    private File profileFile;
    private File mealFile;
    private File workoutFile;
    private File stepFile;
    private Path profilePath;
    private Path mealListPath;
    private Path workoutListPath;
    private Path stepListPath;


    /**
     * Constructs storage. Creates new file
     * in a directory called data if none exist.
     */
    public Storage() {
        this.profileFile = new File(PROFILE_FILE_PATH);
        this.mealFile = new File(MEAL_LIST_FILE_PATH);
        this.workoutFile = new File(WORKOUT_LIST_FILE_PATH);
        this.stepFile = new File(STEP_LIST_FILE_PATH);

        assert profileFile != null;
        assert mealFile != null;
        assert workoutFile != null;
        assert stepFile != null;

        try {
            File f = new File(FILE_DIRECTORY);

            if (f.mkdir()) { // if the directory does not exist, create a new one
                System.out.println("Directory created: " + f.getName());
            }
            if (!this.profileFile.exists()) { // if file that stores profile data does not exist, create one
                profileFile.createNewFile();
            }
            if (!this.mealFile.exists()) { // if file that stores meals does not exist, create one
                mealFile.createNewFile();
            }
            if (!this.workoutFile.exists()) { // if file that stores workouts does not exist, create one
                workoutFile.createNewFile();
            }
            if (!this.stepFile.exists()) { // if file that stores steps does not exist, create one
                stepFile.createNewFile();
            }
        } catch (IOException e) {
            System.out.println("Failed to create directory and file.");
        }
    }

    /**
     * @throws InvalidStorageFilePathException if the given file path is invalid
     */
    public Storage(String profileFilePath, String mealFilePath, String workoutFilePath, String stepFilePath)
            throws InvalidStorageFilePathException {
        profilePath = Paths.get(profileFilePath);
        mealListPath = Paths.get(mealFilePath);
        workoutListPath = Paths.get(workoutFilePath);
        stepListPath = Paths.get(stepFilePath);
        if (!isValidPath(profilePath) || !isValidPath(mealListPath) || !isValidPath(workoutListPath) ||
                !isValidPath(stepListPath)) {
            throw new InvalidStorageFilePathException("Storage file should end with '.txt'");
        }
    }

    /**
     * Returns true if the given path is acceptable as a storage file.
     * The file path is considered acceptable if it ends with '.txt'
     */
    private static boolean isValidPath(Path filePath) {
        return filePath.toString().endsWith(FILE_TYPE);
    }

    /**
     * Saves user profile data into storage
     *
     * @throws IOException error
     */
    public void saveProfile(UserProfile userProfile) throws IOException {
        assert userProfile != null;
        FileWriter file = new FileWriter(PROFILE_FILE_PATH);
        file.write(userProfile + "\n");
        file.close();
    }

    /**
     * Saves meal list into storage
     *
     * @throws IOException error
     */
    public void saveMeals(MealList mealList) throws IOException {
        assert mealList != null;
        ArrayList<Meal> mealArr = mealList.getMealList();
        FileWriter file = new FileWriter(MEAL_LIST_FILE_PATH);
        for (Meal m : mealArr) {
            file.write(m.formatToFile() + "\n");
        }
        file.close();
    }

    /**
     * Saves workout list into storage
     *
     * @throws IOException error
     */
    public void saveWorkouts(WorkoutList workoutList) throws IOException {
        assert workoutList != null;
        ArrayList<Workout> workoutArr = workoutList.getWorkoutList();
        FileWriter file = new FileWriter(WORKOUT_LIST_FILE_PATH);
        for (Workout w : workoutArr) {
            file.write(w.formatToFile() + "\n");
        }
        file.close();
    }

    /**
     * Saves step list into storage
     *
     * @throws IOException error
     */
    public void saveSteps(StepList stepList) throws IOException {
        assert stepList != null;
        ArrayList<Step> stepArr = stepList.getStepList();
        FileWriter file = new FileWriter(STEP_LIST_FILE_PATH);
        for (Step s : stepArr) {
            file.write(s.formatToFile() + "\n");
        }
        file.close();
    }

    /**
     * Save all data when exiting
     *
     * @param userProfile profile of user containing personal data
     * @param mealList list of meals
     * @param workoutList list of workouts
     * @throws IOException error
     */
    public void save(UserProfile userProfile, MealList mealList, WorkoutList workoutList, StepList stepList)
            throws IOException {
        if (userProfile != null) {
            saveProfile(userProfile);
        }
        if (mealList != null) {
            saveMeals(mealList);
        }
        if (workoutList != null) {
            saveWorkouts(workoutList);
        }
        if (stepList != null) {
            saveSteps(stepList);
        }
    }

    /**
     * Loads the {@code UserProfile} data from this profile storage file, and then returns it.
     * Returns an empty {@code UserProfile} if the file does not exist, or is not a regular file.
     *
     * @throws StorageOperationException if there were errors reading and/or converting data from file.
     */
    public UserProfile loadProfile() throws StorageOperationException {
        profilePath = Paths.get(PROFILE_FILE_PATH);
        if (!Files.exists(profilePath) || !Files.isRegularFile(profilePath)) {
            return new UserProfile();
        }

        try {
            return UserProfileDecoder.decodeUserProfile(Files.readAllLines(profilePath), profilePath);
        } catch (FileNotFoundException fnfe) {
            throw new AssertionError("A non-existent file scenario is already handled earlier.");
        } catch (IOException ioe) {
            throw new StorageOperationException("Error writing to file: " + profilePath);
        }
    }

    /**
     * Loads the {@code MealList} data from this meal list storage file, and then returns it.
     * Returns an empty {@code MealList} if the file does not exist, or is not a regular file.
     *
     * @throws StorageOperationException if there were errors reading and/or converting data from file.
     */
    public MealList loadMeals() throws StorageOperationException {
        mealListPath = Paths.get(MEAL_LIST_FILE_PATH);
        if (!Files.exists(mealListPath) || !Files.isRegularFile(mealListPath)) {
            return new MealList();
        }

        try {
            return MealListDecoder.decodeMealList(Files.readAllLines(mealListPath), mealListPath);
        } catch (FileNotFoundException fnfe) {
            throw new AssertionError("A non-existent file scenario is already handled earlier.");
        } catch (IOException ioe) {
            throw new StorageOperationException("Error writing to file: " + mealListPath);
        }
    }

    /**
     * Loads the {@code WorkoutList} data from this workout list storage file, and then returns it.
     * Returns an empty {@code WorkoutList} if the file does not exist, or is not a regular file.
     *
     * @throws StorageOperationException if there were errors reading and/or converting data from file.
     */
    public WorkoutList loadWorkouts() throws StorageOperationException {
        workoutListPath = Paths.get(WORKOUT_LIST_FILE_PATH);
        if (!Files.exists(workoutListPath) || !Files.isRegularFile(workoutListPath)) {
            return new WorkoutList();
        }

        try {
            return WorkoutListDecoder.decodeWorkoutList(Files.readAllLines(workoutListPath), workoutListPath);
        } catch (FileNotFoundException fnfe) {
            throw new AssertionError("A non-existent file scenario is already handled earlier.");
        } catch (IOException ioe) {
            throw new StorageOperationException("Error writing to file: " + workoutListPath);
        }
    }

    /**
     * Loads the {@code StepList} data from this workout list storage file, and then returns it.
     * Returns an empty {@code StepList} if the file does not exist, or is not a regular file.
     *
     * @throws StorageOperationException if there were errors reading and/or converting data from file.
     */
    public StepList loadSteps() throws StorageOperationException {
        stepListPath = Paths.get(STEP_LIST_FILE_PATH);
        if (!Files.exists(stepListPath) || !Files.isRegularFile(stepListPath)) {
            return new StepList();
        }

        try {
            return StepListDecoder.decodeStepList(Files.readAllLines(stepListPath), stepListPath);
        } catch (FileNotFoundException fnfe) {
            throw new AssertionError("A non-existent file scenario is already handled earlier.");
        } catch (IOException ioe) {
            throw new StorageOperationException("Error writing to file: " + stepListPath);
        }
    }

    /**
     * Checks if the profile file contains any profile settings if
     * the file exists. If no data, program will prompt user to input
     * profile data.
     *
     * @return true if file is empty and false otherwise
     */
    public boolean isProfileFileEmpty() {
        try (FileReader reader = new FileReader(profileFile)) {
            int data = reader.read();
            return data == -1; // Returns true if the file is empty
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return false; // Consider it non-empty if there's an exception
        }
    }

    /**
     * Signals that the given file path does not fulfill the storage filepath constraints.
     */
    public static class InvalidStorageFilePathException extends IllegalValueException {
        public InvalidStorageFilePathException(String message) {
            super(message);
        }
    }

    /**
     * Signals that some error has occured while trying to convert and read/write
     * data between the application and the storage file.
     */
    public static class StorageOperationException extends Exception {
        public StorageOperationException(String message) {
            super(message);
        }
    }
}
// @@author
