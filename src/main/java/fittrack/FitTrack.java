package fittrack;

import fittrack.command.Command;
import fittrack.command.CommandResult;
import fittrack.command.ExitCommand;
import fittrack.parser.CommandParser;
import fittrack.parser.ParseException;
import fittrack.storage.Storage;
import fittrack.storage.Storage.StorageOperationException;
import fittrack.storage.Storage.InvalidStorageFilePathException;

import java.io.IOException;


/**
 * Represents the main part of FitTrack.
 * <p>
 * Referenced
 * <a href="https://github.com/se-edu/addressbook-level2/blob/master/src/seedu/addressbook/Main.java">here</a>
 * to build main structure of this class.
 */
public class FitTrack {
    public static final String VERSION = "FitTrack - Version 2.1";

    private final Ui ui;
    private final Storage storage;
    private UserProfile userProfile;
    private MealList mealList;
    private WorkoutList workoutList;
    private StepList stepList;

    private FitTrack(String[] storagePaths) {
        this.ui = new Ui();
        this.storage = initializeStorage(storagePaths);
    }

    /**
     * Main entry-point for the FitTrack application.
     */
    public static void main(String[] args) {
        new FitTrack(args).run();
    }

    private void run() {
        try {
            start();
            loopCommandExecution();
            end();
        } catch (Ui.ForceExitException e) {
            save();
            ui.printForceExit();
        }
    }

    private void start() {
        ui.printVersion();
        ui.printWelcome();
        load();
    }

    private void loopCommandExecution() {
        Command command;
        do {
            String userCommandLine = ui.scanCommandLine();
            command = CommandParser.parseCommand(userCommandLine);

            CommandResult commandResult = executeCommand(command);

            ui.printCommandResult(commandResult);
            ui.printLine();
        } while (!ExitCommand.isExit(command));
    }

    private CommandResult executeCommand(Command command) {
        command.setData(userProfile, mealList, workoutList, stepList);
        CommandResult result = command.execute();
        save();
        return result;
    }

    private void end() {
        save();
    }

    // @@author J0shuaLeong
    private void load() {
        try {
            this.mealList = storage.loadMeals();
        } catch (StorageOperationException e) {
            System.out.println(e.getMessage());
        }

        try {
            this.workoutList = storage.loadWorkouts();
        } catch (StorageOperationException e) {
            System.out.println(e.getMessage());
        }

        try {
            this.stepList = storage.loadSteps();
        } catch (StorageOperationException e) {
            System.out.println(e.getMessage());
        }

        try {
            if (!storage.isProfileFileEmpty()) {
                this.userProfile = storage.loadProfile();
                ui.printWelcomeBackPrompt();
            }
        } catch (StorageOperationException e) {
            System.out.println(e.getMessage());
            ui.printLine();
        }

        if (userProfile == null) {
            initUserProfile();
            ui.printPrompt();
        }
        if (mealList == null) {
            mealList = new MealList();
        }
        if (workoutList == null) {
            workoutList = new WorkoutList();
        }
        if (stepList == null) {
            stepList = new StepList();
        }
        save();
    }
    // @@author

    // @@author J0shuaLeong
    public void initUserProfile() {
        boolean isInputValid = false;
        while (!isInputValid) {
            try {
                String input = ui.scanUserProfile();
                userProfile = UserProfile.parseUserProfile(input);
                ui.printProfileDetails(userProfile);
                isInputValid = true;
            } catch (ParseException e) {
                ui.printException(e);
            }
        }
    }
    // @@author

    /**
     * Creates the StorageFile object based on the user specified path (if any) or the default storage path.
     *
     * @param args arguments supplied by the user at program launch
     */
    // @@author J0shuaLeong
    private Storage initializeStorage(String[] args) {
        if (args.length == 4) {
            try {
                return new Storage(args[0], args[1], args[2], args[3]);
            } catch (InvalidStorageFilePathException e) {
                ui.printStoragePathSettingFailure();
                return new Storage();
            }
        } else if (args.length == 0) {
            return new Storage();
        } else {
            ui.printStoragePathSettingFailure();
            return new Storage();
        }
    }
    // @@author J0shuaLeong

    private void save() {
        try {
            storage.save(userProfile, mealList, workoutList, stepList);
        } catch (IOException e) {
            ui.printSaveFailure();
        }
    }
}
