package fittrack.command;

import fittrack.data.Step;
import fittrack.parser.CommandParser;
import fittrack.parser.IndexOutOfBoundsException;
import fittrack.parser.ParseException;

public class DeleteStepsCommand extends Command{

    public static final String COMMAND_WORD = "deletesteps";
    private static final String DESCRIPTION =
            String.format("`%s` deletes your steps entry from the list.", COMMAND_WORD);
    private static final String USAGE =
            String.format("Type `%s <INDEX>` to delete the step entry by a index.", COMMAND_WORD);
    public static final String HELP = DESCRIPTION + "\n" + USAGE;

    private int stepIndex;

    public DeleteStepsCommand(String commandLine) {
        super(commandLine);
    }

    /**
     * Execute the command.
     *
     * @return result of the execution
     */
    @Override
    public CommandResult execute() {
        if (stepList.isEmpty()) {
            return CommandParser.
                    getInvalidCommandResult(commandLine, IndexOutOfBoundsException.LIST_EMPTY);
        } else if (!stepList.isIndexValid(stepIndex)) {
            return CommandParser.
                    getInvalidCommandResult(commandLine, IndexOutOfBoundsException.INDEX_INVALID);
        }

        Step toDelete = stepList.getStep(stepIndex);
        stepList.deleteStep(stepIndex);
        return new CommandResult("I've deleted the following step entry:" + "\n" + toDelete.toString());
    }

    /**
     * Apply arguments to its field using parser.
     *
     * @param args arguments as a string
     * @throws ParseException if parse fails
     */
    @Override
    public void setArguments(String args) throws ParseException {
        stepIndex = CommandParser.parseIndex(args);
    }

    /**
     * Returns help of the command.
     *
     * @return help
     */
    @Override
    protected String getHelp() {
        return HELP;
    }
}
