package fittrack;

import fittrack.command.Command;
import fittrack.command.CommandResult;
import fittrack.command.ExitCommand;
import fittrack.parser.*;
import fittrack.parser.NumberFormatException;
import fittrack.storage.Storage;
import fittrack.storage.Storage.StorageOperationException;
import fittrack.storage.Storage.InvalidStorageFilePathException;


/**
 * Represents the main part of FitTrack.
 * <p>
 * Referenced
 * <a href="https://github.com/se-edu/addressbook-level2/blob/master/src/seedu/addressbook/Main.java">here</a>
 * to build main structure of this class.
 */
public class FitTrack {

    public static final String VERSION = "FitTrack - Version 2.0";
    private final Ui ui;
    private Storage storage;
    private UserProfile userProfile;
    private MealList mealList;
    private WorkoutList workoutList;

    private FitTrack() {
        ui = new Ui();
        userProfile = new UserProfile();
        mealList = new MealList();
        workoutList = new WorkoutList();
    }

    /**
     * Main entry-point for the FitTrack application.
     */
    public static void main(String[] args) {
        new FitTrack().run(args);
    }

    private void run(String[] args) {
        start(args);
        loopCommandExecution();
        end();
    }

    private void start(String[] args) {
        boolean isValidInput = false;
        ui.printVersion(VERSION);
        ui.printWelcome();

        try {
            this.storage = initializeStorage(args);
            if (!storage.isProfileFileEmpty()) {
                this.userProfile = storage.profileLoad();
                ui.printPrompt();
                isValidInput = true;
            }
            this.mealList = storage.mealLoad();
            this.workoutList = storage.workoutLoad();
        } catch (StorageOperationException | InvalidStorageFilePathException e) {
            System.out.println("There was a problem with the loading of storage contents.");
            ui.printLine();
        }


        while (!isValidInput) {
            try {
                profileSettings();
                isValidInput = true;
            } catch (PatternMatchFailException e) {
                System.out.println("Wrong format. Please enter h/<height> w/<weight> g/<gender> l/<dailyCalorieLimit>");
            } catch (NumberFormatException e) {
                System.out.println("Please enter numbers for height, weight, and daily calorie limit.");
            } catch (NegativeNumberException e) {
                System.out.println("Please enter a number greater than 0");
            } catch (WrongGenderException e) {
                System.out.println("Please enter either M or F");
            }
        }
    }

    private void loopCommandExecution() {
        Command command;
        do {
            String userCommandLine = ui.scanCommandLine();
            command = new CommandParser().parseCommand(userCommandLine);
            CommandResult commandResult = executeCommand(command);
            ui.printCommandResult(commandResult);
        } while (!ExitCommand.isExit(command));
    }

    private CommandResult executeCommand(Command command) {
        try {
            command.setData(userProfile, mealList, workoutList, storage);
            storage.save(userProfile, mealList, workoutList);
            return command.execute();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException();
        }
    }

    /**
     * Gets user profile details when program starts.
     *
     * @throws PatternMatchFailException if regex match fails
     * @throws NumberFormatException if one of arguments is not double
     */
    private void profileSettings()
            throws PatternMatchFailException, NumberFormatException, NegativeNumberException, WrongGenderException {
        System.out.println(
                "Please enter your height (in cm), weight (in kg), gender (M or F), and daily calorie limit (in kcal):"
        );
        String input = ui.scanNextLine();

        assert (input != null) : "Profile cannot be null";

        UserProfile profile = new CommandParser().parseProfile(input);
        userProfile.setHeight(profile.getHeight());
        userProfile.setWeight(profile.getWeight());
        userProfile.setDailyCalorieLimit(profile.getDailyCalorieLimit());
        userProfile.setGender(profile.getGender());

        ui.printProfileDetails(userProfile);
    }

    /**
     * Creates the StorageFile object based on the user specified path (if any) or the default storage path.
     *
     * @param args arguments supplied by the user at program launch
     * @throws InvalidStorageFilePathException if the target file path is incorrect.
     */
    private Storage initializeStorage(String[] args) throws InvalidStorageFilePathException {
        boolean isStorageFileSpecifiedByUser = args.length > 0;
        return isStorageFileSpecifiedByUser ? new Storage(args[0], args[1], args[2]) : new Storage();
    }

    private void end() {
    }
}
