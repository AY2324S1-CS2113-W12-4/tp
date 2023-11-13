package fittrack.command;

import fittrack.data.Calories;
import fittrack.data.Date;
import fittrack.data.Meal;
import fittrack.parser.DateFormatException;

// @@author farissirraj
public class CaloriesConsumedCommand extends Command {
    public static final String COMMAND_WORD = "caloriesconsumed";
    private static final String DESCRIPTION =
            String.format("`%s` shows your total calories consumed on a specific date.", COMMAND_WORD);
    private static final String USAGE = String.format(
            "Type `%s <DATE>` to see the total calories consumed on that date.\n" +
                    "You should type <DATE> in format of `yyyy-MM-dd`.",
            COMMAND_WORD
    );
    public static final String HELP = DESCRIPTION + "\n" + USAGE;

    private Date date;
    private Calories caloriesConsumed;

    public CaloriesConsumedCommand(String commandLine) {
        super(commandLine);
    }

    @Override
    public CommandResult execute() {
        StringBuilder feedbackBuilder = new StringBuilder();
        caloriesConsumed = new Calories(0);

        for (Meal meal: mealList.getMealList()) {
            if (date.equals(meal.getDate())) {
                caloriesConsumed = caloriesConsumed.sum(meal.getCalories());
                feedbackBuilder.append(meal).append("\n");
            }
        }

        String summary = "Total calories consumed on " + date + ": " + caloriesConsumed;
        feedbackBuilder.append(summary);
        return new CommandResult(feedbackBuilder.toString());
    }

    @Override
    public void setArguments(String args) throws DateFormatException {
        date = Date.parseDate(args);
    }

    @Override
    protected String getHelp() {
        return HELP;
    }

    Calories getCaloriesConsumed() {
        return caloriesConsumed;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
// @@author
