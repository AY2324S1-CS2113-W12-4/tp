package fittrack.command;

import fittrack.Ui;
import fittrack.data.Meal;
import fittrack.parser.CommandParser;
import fittrack.parser.PatternMatchFailException;

import java.util.ArrayList;

public class FindMealCommand extends Command {

    public static final String COMMAND_WORD = "findmeal";
    private static final String DESCRIPTION =
            String.format("`%s` finds the meal you are looking for in your meal list.", COMMAND_WORD);
    private static final String USAGE = String.format("Type `%s <keyword>` to find a meal.\n", COMMAND_WORD);

    public static final String HELP = DESCRIPTION + "\n" + USAGE;
    private Ui ui = new Ui();
    private String keyword;

    public FindMealCommand(String commandLine) {
        super(commandLine);
    }

    @Override
    public CommandResult execute() {
        ArrayList<Meal> meals = mealList.getMealList();
        int mealNum = 0;
        int numFound = 0;
        boolean mealFound = false;
        for (Meal meal : meals) {
            if (meal.getName().contains(keyword)) {
                if (!mealFound) {
                    mealFound = true;
                    ui.printFoundMessage("meals", keyword);
                }
                ui.printMealWithNumber(mealNum, meal);
                numFound++;
            }
            mealNum++;
        }
        if (!mealFound) {
            return new CommandResult("Sorry, there are no such meals found.");
        }
        return new CommandResult("There are " + numFound + " meals that contains " + keyword);
    }

    @Override
    public void setArguments(String args, CommandParser parser)
            throws PatternMatchFailException {
        if (args.isEmpty()) {
            throw new PatternMatchFailException();
        }
        keyword = parser.parseFind(args);
    }

    @Override
    protected String getHelp() {
        return HELP;
    }
}
