package fittrack.storage;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;


public class StorageTest {

    private static final String TEST_DATA_FOLDER = "test/data/StorageTest";

    @Test
    public void constructor_nullFilePath_exceptionThrown() {
        assertThrows(NullPointerException.class,
                () -> new Storage(null, null, null, null));
    }

    //TODO fix test
    /*@Test
    public void load_invalidProfileFormat_exceptionThrown() throws Exception {
        // The file contains valid txt data, but does not match the format
        Storage storage = getStorage("InvalidProfileData.txt",
                "InvalidMealListData.txt", "InvalidWorkoutListData.txt", "InvalidStepListData.txt");
        assertThrows(Ui.ForceExitException.class, () -> storage.loadProfile());
    }*/

    @Test
    public void save_nullAddressBook_exceptionThrown() {
        Storage storage = new Storage();
        assertThrows(AssertionError.class, () -> storage.saveProfile(null));
        assertThrows(AssertionError.class, () -> storage.saveMeals(null));
        assertThrows(AssertionError.class, () -> storage.saveWorkouts(null));
    }

    private Storage getStorage(String profileFileName, String mealFileName, String workoutFileName, String stepFileName)
            throws Exception {
        return new Storage(TEST_DATA_FOLDER + "/" + profileFileName,
                TEST_DATA_FOLDER + "/" + mealFileName,
                TEST_DATA_FOLDER + "/" + workoutFileName,
                TEST_DATA_FOLDER + "/" + stepFileName);
    }
}
