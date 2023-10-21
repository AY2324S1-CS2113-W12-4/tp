package fittrack;

import fittrack.command.CommandResult;

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
        return in.nextLine();
    }

    /**
     * Scans a command line from the user input.
     *
     * @return command line as a line of string
     */
    public String scanCommandLine() {
        return scanNextLine();
    }

    public void printBlankLine() {
        System.out.println();
    }

    public void printLine() {
        System.out.println(LINE);
    }

    public void printWelcome() {
        System.out.println("Welcome to FitTrack!");
        System.out.println(LOGO);
        printLine();
    }

    public void printCommandResult(CommandResult commandResult) {
        System.out.println(commandResult.getFeedback());
        printBlankLine();
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
}
