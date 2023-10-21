package fittrack.command;

import fittrack.parser.CommandParser;

public class BmiCommand extends Command {
    public static final String COMMAND_WORD = "bmi";
    private static final String DESCRIPTION =
            String.format("`%s` calculates your current BMI.", COMMAND_WORD);
    private static final String USAGE =
            String.format("Type `%s` to view your BMI.", COMMAND_WORD);
    public static final String HELP = DESCRIPTION + "\n" + USAGE;

    @Override
    public CommandResult execute() {
        return new CommandResult(String.format("Your current BMI is %.2f", userProfile.getBmi()));
    }

    @Override
    public void setArguments(String args, CommandParser parser) {
    }

    @Override
    protected String getHelp() {
        return HELP;
    }
}
