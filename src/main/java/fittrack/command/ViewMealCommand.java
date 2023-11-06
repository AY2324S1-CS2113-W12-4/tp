package fittrack.command;

import fittrack.parser.CommandParser;
import fittrack.parser.PatternMatchFailException;


public class ViewMealCommand extends Command {
    public static final String COMMAND_WORD = "viewmeal";
    private static final String DESCRIPTION =
            String.format("`%s` shows the list of all meals.", COMMAND_WORD);
    private static final String USAGE =
            String.format("Type `%s` to view the list of meals.", COMMAND_WORD);
    public static final String HELP = DESCRIPTION + "\n" + USAGE;

    public ViewMealCommand(String commandLine) {
        super(commandLine);
    }

    @Override
    public CommandResult execute() {
        String feedback = "These are the meals you have consumed:\n" + mealList.toString();
        return new CommandResult(feedback);
    }

    @Override
    public void setArguments(String args, CommandParser parser) throws PatternMatchFailException {
        if (!args.isEmpty()) {
            throw new PatternMatchFailException();
        }
    }

    @Override
    protected String getHelp() {
        return HELP;
    }
}
