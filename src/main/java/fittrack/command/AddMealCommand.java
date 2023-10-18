package fittrack.command;

import fittrack.Meal;
import fittrack.MealList;
import fittrack.parser.CommandParser;
import fittrack.parser.NumberFormatException;
import fittrack.parser.PatternMatchFailException;

public class AddMealCommand extends Command {
    public static final String COMMAND_WORD = "addmeal";
    private static final String DESCRIPTION =
            String.format("`%s` adds your daily meal data to the list.", COMMAND_WORD);
    private static final String USAGE =
            String.format("Type `%s <MEAL_NAME> c/<CALORIES>` to add a meal.", COMMAND_WORD);
    public static final String HELP = DESCRIPTION + "\n" + USAGE;

    private Meal newMeal;

    @Override
    public CommandResult execute() {
        MealList.addToList(newMeal);
        return new CommandResult("I've added the following meal:" + "\n" + newMeal.toString());
    }

    @Override
    public void setArguments(String args, CommandParser parser)
            throws PatternMatchFailException, NumberFormatException {
        newMeal = parser.parseMeal(args);
    }

    @Override
    protected String getHelp() {
        return HELP;
    }
}
