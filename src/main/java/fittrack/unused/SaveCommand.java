// @@author J0shuaLeong
package fittrack.unused;

import fittrack.command.Command;
import fittrack.command.CommandResult;
import fittrack.parser.PatternMatchFailException;

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
        //        try {
        //            storage.save(userProfile, mealList, workoutList, stepList);
        //        } catch (IOException e) {
        //            System.out.println(e.getMessage());
        //        }
        return new CommandResult("Your data has been saved!");
    }


    @Override
    public void setArguments(String args) throws PatternMatchFailException {
        if (!args.isEmpty()) {
            throw new PatternMatchFailException();
        }
    }

    @Override
    protected String getHelp() {
        return HELP;
    }
}
// @@author
