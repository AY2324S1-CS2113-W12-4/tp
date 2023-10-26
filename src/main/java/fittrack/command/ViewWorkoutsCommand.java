package fittrack.command;

import fittrack.parser.CommandParser;
import fittrack.parser.PatternMatchFailException;

public class ViewWorkoutsCommand extends Command {
    public static final String COMMAND_WORD = "viewworkouts";
    private static final String DESCRIPTION =
            String.format("`%s` shows the list of all workouts.", COMMAND_WORD);
    private static final String USAGE =
            String.format("Type `%s` to view the list of your workouts.", COMMAND_WORD);
    public static final String HELP = DESCRIPTION + "\n" + USAGE;

    public ViewWorkoutsCommand(String commandLine) {
        super(commandLine);
    }

    /**
     * Execute the command
     * @return list of workouts
     */
    @Override
    public CommandResult execute() {
        String feedback = "These are the workouts you have done:\n" +
                workoutList.toString();

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

