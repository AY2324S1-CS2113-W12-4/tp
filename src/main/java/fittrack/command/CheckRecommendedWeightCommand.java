package fittrack.command;

import fittrack.data.Height;
import fittrack.parser.ParseException;
import fittrack.parser.PatternMatchFailException;

public class CheckRecommendedWeightCommand extends Command{

    public static final String COMMAND_WORD = "checkrecommendedweight";
    private static final String DESCRIPTION =
            String.format("`%s` calculates the recommended weight for your height.", COMMAND_WORD);
    private static final String USAGE = String.format(
            "Type `%s` calculate the recommended weight for your height.", COMMAND_WORD);
    public static final String HELP = DESCRIPTION + "\n" + USAGE;

    public CheckRecommendedWeightCommand(String commandLine) {
        super(commandLine);
    }

    @Override
    public CommandResult execute(){
        Height height = userProfile.getHeight();
        double weight = height.calculateIdealWeight();
        return new CommandResult(String.format("Recommended Weight: %.2f kg", weight));
    }

    @Override
    public void setArguments(String args) throws ParseException {
        if (!args.isEmpty()) {
            throw new PatternMatchFailException();
        }
    }

    @Override
    protected String getHelp() {
        return HELP;
    }
}
