package fittrack.command;

import fittrack.data.Meal;
import fittrack.parser.CommandParser;

public class DeleteMealCommand extends Command {
    public static final String COMMAND_WORD = "deletemeal";
    private static final String DESCRIPTION =
            String.format("`%s` deletes your daily meal data from the list.", COMMAND_WORD);
    private static final String USAGE =
            String.format("Type `%s <INDEX>` to delete the meal by an index.", COMMAND_WORD);
    public static final String HELP = DESCRIPTION + "\n" + USAGE;

    private int mealIndex;

    @Override
    public CommandResult execute() {
        Meal toDelete = mealList.getMeal(mealIndex);
        mealList.deleteMeal(mealIndex);
        return new CommandResult("I've deleted the following meal:" + "\n" + toDelete.toString());
    }

    @Override
    public void setArguments(String args, CommandParser parser) {
        // TODO: Try to make parse method in CommandParser and
        // TODO: use the method by parser.parseXXX();
        // TODO: Refer to CommandParser.parseProfile().

        mealIndex = Integer.parseInt(args);
    }

    @Override
    protected String getHelp() {
        return HELP;
    }
}
