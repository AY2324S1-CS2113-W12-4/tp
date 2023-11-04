package fittrack.command;

import fittrack.MealList;
import fittrack.UserProfile;
import fittrack.WorkoutList;
import fittrack.storage.Storage;
import fittrack.parser.CommandParser;
import fittrack.parser.ParseException;

public abstract class Command {
    protected String commandLine;
    protected UserProfile userProfile;
    protected MealList mealList;
    protected WorkoutList workoutList;
    protected Storage storage;

    public Command(String commandLine) {
        this.commandLine = commandLine;
    }

    /**
     * Set data of the command for execution.
     *
     * @param userProfile user profile
     * @param mealList meal list
     * @param workoutList work list
     */
    public void setData(UserProfile userProfile, MealList mealList, WorkoutList workoutList, Storage storage) {
        this.userProfile = userProfile;
        this.mealList = mealList;
        this.workoutList = workoutList;
        this.storage = storage;
    }

    /**
     * Execute the command.
     *
     * @return result of the execution
     */
    public abstract CommandResult execute();

    /**
     * Apply arguments to its field using parser.
     *
     * @param args arguments as a string
     * @param parser parser
     * @throws ParseException if parse fails
     */
    public abstract void setArguments(String args, CommandParser parser)
            throws ParseException;

    /**
     * Returns help of the command.
     *
     * @return help
     */
    protected abstract String getHelp();
}
