package fittrack.command;

import fittrack.parser.PatternMatchFailException;

public class BmiCommand extends Command {
    public static final String COMMAND_WORD = "bmi";
    private static final String DESCRIPTION =
            String.format("`%s` calculates your current BMI.", COMMAND_WORD);
    private static final String USAGE =
            String.format("Type `%s` to view your BMI.", COMMAND_WORD);
    public static final String HELP = DESCRIPTION + "\n" + USAGE;

    public BmiCommand(String commandLine) {
        super(commandLine);
    }

    @Override
    public CommandResult execute() {
        return new CommandResult(String.format("Your current BMI is %S", userProfile.getBmi()) +
                "\n" + String.format("BMI falls under %S category", userProfile.getBmiCategory()));
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
