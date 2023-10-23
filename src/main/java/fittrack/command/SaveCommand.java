package fittrack.command;

import fittrack.parser.CommandParser;

import java.io.IOException;

public class SaveCommand extends Command {
    public static final String COMMAND_WORD = "save";
    private static final String DESCRIPTION =
            String.format("`%s` saves your profile, meals and workout data.", COMMAND_WORD);
    private static final String USAGE =
            String.format("Type `%s` to save your profile, meals and workout data.", COMMAND_WORD);
    public static final String HELP = DESCRIPTION + "\n" + USAGE;

    public SaveCommand(String commandLine) {
        super(commandLine);
    }

    @Override
    public CommandResult execute() {
        try {
            storage.saveProfile(userProfile);
            storage.saveMeals(mealList);
            storage.saveWorkouts(workoutList);
        } catch (IOException e) {
            System.out.println(e);
        }
        return new CommandResult("Your data has been saved!");
    }

    @Override
    public void setArguments(String args, CommandParser parser) {
    }

    @Override
    protected String getHelp() {
        return HELP;
    }
}
