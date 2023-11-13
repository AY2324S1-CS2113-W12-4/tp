package fittrack.command;

import fittrack.parser.PatternMatchFailException;

public class ViewStepsCommand extends Command {
    public static final String COMMAND_WORD = "viewsteps";
    private static final String DESCRIPTION =
            String.format("`%s` shows the list of all steps.", COMMAND_WORD);
    private static final String USAGE =
            String.format("Type `%s` to view the list of your steps.", COMMAND_WORD);
    public static final String HELP = DESCRIPTION + "\n" + USAGE;

    public ViewStepsCommand(String commandLine) {
        super(commandLine);
    }

    /**
     * Execute the command
     * @return list of workouts
     */
    @Override
    public CommandResult execute() {
        String feedback = "These are the steps you have done:\n" +
                stepList.toString();

        return new CommandResult(feedback);
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

