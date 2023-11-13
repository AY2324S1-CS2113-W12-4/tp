package fittrack.command;

import fittrack.data.Meal;
import fittrack.parser.CommandParser;
import fittrack.parser.IndexOutOfBoundsException;
import fittrack.parser.ParseException;

public class DeleteMealCommand extends Command {
    public static final String COMMAND_WORD = "deletemeal";
    private static final String DESCRIPTION =
            String.format("`%s` deletes your daily meal data from the list.", COMMAND_WORD);
    private static final String USAGE =
            String.format("Type `%s <INDEX>` to delete the meal by an index.", COMMAND_WORD);
    public static final String HELP = DESCRIPTION + "\n" + USAGE;

    private int mealIndex;

    public DeleteMealCommand(String commandLine) {
        super(commandLine);
    }

    // @@author NgLixuanNixon
    @Override
    public CommandResult execute() {
        if (mealList.isEmpty()) {
            return CommandParser.
                    getInvalidCommandResult(commandLine, IndexOutOfBoundsException.LIST_EMPTY);
        } else if (!mealList.isIndexValid(mealIndex)) {
            return CommandParser.
                    getInvalidCommandResult(commandLine, IndexOutOfBoundsException.INDEX_INVALID);
        }

        Meal toDelete = mealList.getMeal(mealIndex);
        mealList.deleteMeal(mealIndex);
        return new CommandResult("I've deleted the following meal:" + "\n" + toDelete.toString());
    }
    // @@author

    // @@author NgLixuanNixon
    @Override
    public void setArguments(String args) throws ParseException {
        mealIndex = CommandParser.parseIndex(args);
    }
    // @@author

    @Override
    protected String getHelp() {
        return HELP;
    }
}
