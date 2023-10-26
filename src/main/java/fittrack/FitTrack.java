package fittrack;

import fittrack.command.Command;
import fittrack.command.CommandResult;
import fittrack.command.ExitCommand;
import fittrack.parser.CommandParser;
import fittrack.parser.NegativeNumberException;
import fittrack.parser.NumberFormatException;
import fittrack.parser.PatternMatchFailException;
import fittrack.storage.Storage;
import fittrack.storage.Storage.StorageOperationException;


/**
 * Represents the main part of FitTrack.
 * <p>
 * Referenced
 * <a href="https://github.com/se-edu/addressbook-level2/blob/master/src/seedu/addressbook/Main.java">here</a>
 * to build main structure of this class.
 */
public class FitTrack {

    private final Ui ui;
    private final Storage storage;
    private UserProfile userProfile;
    private MealList mealList;
    private WorkoutList workoutList;


    private FitTrack() {
        ui = new Ui();
        storage = new Storage();
        userProfile = new UserProfile();
        mealList = new MealList();
        workoutList = new WorkoutList();
    }

    /**
     * Main entry-point for the FitTrack application.
     */
    public static void main(String[] args) throws StorageOperationException {
        new FitTrack().run();
    }

    private void run() throws StorageOperationException {
        start();
        loopCommandExecution();
        end();
    }

    private void start() {
        ui.printWelcome();
        boolean isValidInput = false;

        try {
            if (!storage.isProfileFileEmpty()) {
                this.userProfile = storage.profileLoad();
                ui.printPrompt();
                isValidInput = true;
            }
            this.mealList = storage.mealLoad();
            this.workoutList = storage.workoutLoad();
        }catch (StorageOperationException e) {
            throw new RuntimeException(e);
        }

        while (!isValidInput) {
            try {
                profileSettings();
                isValidInput = true;
            } catch (PatternMatchFailException e) {
                System.out.println("Wrong format. Please enter h/<height> w/<weight> l/<dailyCalorieLimit>");
            } catch (NumberFormatException e) {
                System.out.println("Please enter numbers for height, weight, and daily calorie limit.");
            } catch (NegativeNumberException e) {
                System.out.println("Please enter a number greater than 0");
            }
        }
    }

    private void loopCommandExecution() throws StorageOperationException {
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
            throws PatternMatchFailException, NumberFormatException, NegativeNumberException {
        System.out.println(
                "Please enter your height (in cm), weight (in kg), and daily calorie limit (in kcal):"
        );
        String input = ui.scanNextLine();

        assert (input != null) : "Profile cannot be null";

        UserProfile profile = new CommandParser().parseProfile(input);
        userProfile.setHeight(profile.getHeight());
        userProfile.setWeight(profile.getWeight());
        userProfile.setDailyCalorieLimit(profile.getDailyCalorieLimit());

        ui.printProfileDetails(userProfile);
    }

    private void end() {
    }
}
