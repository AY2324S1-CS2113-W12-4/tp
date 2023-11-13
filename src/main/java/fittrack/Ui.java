package fittrack;

import fittrack.command.CommandResult;

import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Represents the user interface of FitTrack.
 */
public class Ui {
    private static final String LOGO = "___________.__  __ ___________                     __\n"
                                     + "\\_   _____/|__|/  |\\__    ___/___________    ____ |  | __\n"
                                     + " |    __)  |  \\   __\\|    |  \\_  __ \\__  \\ _/ ___\\|  |/ /\n"
                                     + " |     \\   |  ||  |  |    |   |  | \\/ __ \\  \\___|    <\n"
                                     + " \\___  /   |__||__|  |____|   |__|  (____  /\\___  >__|_ \\";
    private static final String LINE = "____________________________________________________________";

    private final Scanner in;

    /**
     * Constructs UI of FitTrack.
     */
    public Ui() {
        in = new Scanner(System.in);
    }

    /**
     * Scans a line from the user input.
     *
     * @return user input as a line of string
     */
    public String scanNextLine() {
        try {
            return in.nextLine();
        } catch (NoSuchElementException e) {
            // When user interrupts or inputs EOF
            throw new ForceExitException();
        }
    }

    /**
     * Scans a command line from the user input.
     *
     * @return command line as a line of string
     */
    public String scanCommandLine() {
        return scanNextLine();
    }

    // @@author J0shuaLeong
    public String scanUserProfile() {
        System.out.println(
                "\nPlease enter your height (in cm), weight (in kg), " +
                        "gender (M or F), and daily calorie limit (in kcal).\n" +
                        "Enter in format of `h/<HEIGHT> w/<WEIGHT> g/<GENDER> l/<CALORIE_LIMIT>`."
        );
        return scanNextLine();
    }
    // @@author

    public void printLine() {
        System.out.println(LINE);
    }

    public void printWelcome() {
        System.out.println("Welcome to FitTrack!");
        System.out.println(LOGO);
        printLine();
    }

    public void printVersion() {
        System.out.println(FitTrack.VERSION);
    }

    public void printCommandResult(CommandResult commandResult) {
        System.out.println(commandResult.getFeedback());
    }

    public void printWelcomeBackPrompt() {
        System.out.println("Welcome back! How can I help you today?");
        printLine();
    }

    public void printPrompt() {
        System.out.println("Hello and welcome! How can I help you today?");
        printLine();
    }

    /**
     * Prints the profile details of the user after user has
     * entered details for the first time.
     *
     * @param profile user profile
     */
    public void printProfileDetails(UserProfile profile) {
        System.out.println("Here are your profile settings.");
        System.out.println("Height: " + profile.toString());
        printLine();
    }

    public void printStoragePathSettingFailure() {
        System.out.println("One of given storage paths is invalid. Proceeding with default paths.");
    }

    public void printSaveFailure() {
        System.out.println("Failed to save data.");
    }

    public void printException(Exception e) {
        System.out.println(e.getMessage());
    }

    public void printForceExit() {
        System.out.println("Please fix the corrupted file which can be found in" +
                " data directory. Exiting the app...");
    }

    public static boolean createNewFile() {
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine().trim();
        while (!line.equalsIgnoreCase("y") && !line.equalsIgnoreCase("n")) {
            System.out.println("Unknown input. Please enter Y or N only.");
            line = scanner.nextLine().trim();
        }

        return line.equalsIgnoreCase("y");
    }

    public static void printPromptForCreateNewFile(String fileName) {
        System.out.println(String.format(
                "\nThe %s file is corrupted. Would you like to create a new one? (Y/N)", fileName));
    }

    public static class ForceExitException extends RuntimeException {
    }
}
