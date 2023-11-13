package fittrack.command;

import fittrack.data.Meal;
import fittrack.parser.CommandParser;
import fittrack.parser.PatternMatchFailException;

import java.util.ArrayList;

// @@author J0shuaLeong
public class FindMealCommand extends Command {

    public static final String COMMAND_WORD = "findmeal";
    private static final String DESCRIPTION =
            String.format("`%s` finds the meal you are looking for in your meal list.", COMMAND_WORD);
    private static final String USAGE = String.format("Type `%s <KEYWORD>` to find a meal.", COMMAND_WORD);

    public static final String HELP = DESCRIPTION + "\n" + USAGE;

    private String keyword;

    public FindMealCommand(String commandLine) {
        super(commandLine);
    }

    @Override
    public CommandResult execute() {
        StringBuilder feedbackBuilder = new StringBuilder();

        ArrayList<Meal> meals = mealList.getMealList();
        int mealNum = 0;
        int numFound = 0;
        boolean mealFound = false;
        for (Meal meal : meals) {
            if (meal.getName().contains(keyword)) {
                if (!mealFound) {
                    mealFound = true;
                    String foundMessage = "These meals contain the keyword " + keyword + ":";
                    feedbackBuilder.append(foundMessage).append("\n");
                }
                String mealWithNumber = (mealNum + 1) + "." + meal;
                feedbackBuilder.append(mealWithNumber).append("\n");
                numFound++;
            }
            mealNum++;
        }
        if (!mealFound) {
            return new CommandResult("Sorry, there are no such meals found.");
        }

        String summary = "There are " + numFound + " meals that contains " + keyword + ".";
        feedbackBuilder.append(summary);
        return new CommandResult(feedbackBuilder.toString());
    }

    @Override
    public void setArguments(String args) throws PatternMatchFailException {
        keyword = CommandParser.parseKeyword(args);
    }

    @Override
    protected String getHelp() {
        return HELP;
    }
}
// @@author J0shuaLeong
