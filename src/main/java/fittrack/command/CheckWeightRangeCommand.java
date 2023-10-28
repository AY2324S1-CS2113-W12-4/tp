package fittrack.command;

import fittrack.data.Height;
import fittrack.parser.CommandParser;
import fittrack.parser.ParseException;

public class CheckWeightRangeCommand extends Command{

    public static final String COMMAND_WORD = "checkweightrange";
    private static final String DESCRIPTION =
            String.format("`%s` calculates the recommended weight for your height.", COMMAND_WORD);
    private static final String USAGE = String.format(
            "Type `%s` calculate the recommended weight for your height.\n", COMMAND_WORD);
    public static final String HELP = DESCRIPTION + "\n" + USAGE;

    public CheckWeightRangeCommand(String commandLine) {
        super(commandLine);
    }

    @Override
    public CommandResult execute(){
        Height height = userProfile.getHeight();
        double weight = height.calculateIdealWeight();
        return new CommandResult("Recommended Weight: " + weight + " kg");
    }

    @Override
    public void setArguments(String args, CommandParser parser) throws ParseException {
    }

    @Override
    protected String getHelp() {
        return HELP;
    }
}
