package fittrack.command;

import fittrack.data.Meal;
import fittrack.parser.CommandParser;
import fittrack.parser.NumberFormatException;
import fittrack.parser.PatternMatchFailException;

public class AddMealCommand extends Command {
    public static final String COMMAND_WORD = "addmeal";
    private static final String DESCRIPTION =
            String.format("`%s` adds your daily meal data to the list.", COMMAND_WORD);
    private static final String USAGE = String.format(
            "Type `%s <MEAL_NAME> c/<CALORIES>` to add today's meal.\n" +
                    "Type `%s <MEAL_NAME> c/<CALORIES> d/<DATE>` to add a meal.\n" +
                    "You should type <DATE> in format of `yyyy-MM-dd`.",
            COMMAND_WORD, COMMAND_WORD
    );
    public static final String HELP = DESCRIPTION + "\n" + USAGE;

    private Meal newMeal;

    public AddMealCommand(String commandLine) {
        super(commandLine);
    }

    @Override
    public CommandResult execute() {
        mealList.addToList(newMeal);
        return new CommandResult("I've added the following meal:" + "\n" + newMeal.toString());
    }

    @Override
    public void setArguments(String args, CommandParser parser)
            throws PatternMatchFailException, NumberFormatException {
        newMeal = parser.parseMeal(args);
    }

    public Meal getMeal(){
        return newMeal;
    }

    @Override
    protected String getHelp() {
        return HELP;
    }
}
