package fittrack.command;

import fittrack.parser.PatternMatchFailException;

public class ViewProfileCommand extends Command {
    public static final String COMMAND_WORD = "viewprofile";
    private static final String DESCRIPTION =
            String.format("`%s` shows all profile details.", COMMAND_WORD);
    private static final String USAGE =
            String.format("Type `%s` to view your profile.", COMMAND_WORD);
    public static final String HELP = DESCRIPTION + "\n" + USAGE;

    public ViewProfileCommand(String commandLine) {
        super(commandLine);
    }

    @Override
    public CommandResult execute() {
        String feedback = "Your Profile:\n" + userProfile.toString();
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
