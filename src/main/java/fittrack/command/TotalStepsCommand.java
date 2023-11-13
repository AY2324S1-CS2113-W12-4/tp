package fittrack.command;

import fittrack.data.Date;
import fittrack.data.Step;
import fittrack.parser.ParseException;

public class TotalStepsCommand extends Command {
    public static final String COMMAND_WORD = "totalsteps";
    private static final String DESCRIPTION =
            String.format("`%s` shows the total number of steps taken on a specific date.", COMMAND_WORD);
    private static final String USAGE = String.format(
            "Type `%s` <DATE> to show the total number of steps walked on that date.\n."+
            "You should type <DATE> in format of `yyyy-MM-dd`.",
            COMMAND_WORD
    );
    public static final String HELP = DESCRIPTION + "\n" + USAGE;

    private Date date;

    public TotalStepsCommand(String commandLine) {
        super(commandLine);
    }

    @Override
    public CommandResult execute() {
        Step totalSteps = new Step(0, null);
        for (Step step: stepList.getStepList()) {
            if (date.equals(step.getDate())) {
                totalSteps = totalSteps.sum(step);
            }
        }
        return new CommandResult("Total steps taken: " + totalSteps.getSteps() + " steps");
    }

    /**
     * Apply arguments to its field using parser.
     *
     * @param args   arguments as a string
     * @throws ParseException if parse fails
     */
    @Override
    public void setArguments(String args) throws ParseException {
        date = Date.parseDate(args);
    }


    @Override
    protected String getHelp() {
        return HELP;
    }
}
