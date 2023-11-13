package fittrack.command;

import fittrack.data.Calories;
import fittrack.data.Date;
import fittrack.data.Workout;
import fittrack.parser.DateFormatException;

// @@author NgLixuanNixon
public class CaloriesBurntCommand extends Command {
    public static final String COMMAND_WORD = "caloriesburnt";
    private static final String DESCRIPTION =
            String.format("`%s` shows your total calories burnt on a specific date.", COMMAND_WORD);
    private static final String USAGE = String.format(
            "Type `%s <DATE>` to see the total calories burnt on that date.\n" +
                    "You should type <DATE> in format of `yyyy-MM-dd`.",
            COMMAND_WORD
    );
    public static final String HELP = DESCRIPTION + "\n" + USAGE;

    private Date date;
    private Calories caloriesBurnt;

    public CaloriesBurntCommand(String commandLine) {
        super(commandLine);
    }

    @Override
    public CommandResult execute() {
        StringBuilder feedbackBuilder = new StringBuilder();
        caloriesBurnt = new Calories(0);

        for (Workout workout: workoutList.getWorkoutList()) {
            if (date.equals(workout.getDate())) {
                caloriesBurnt = caloriesBurnt.sum(workout.getCalories());
                feedbackBuilder.append(workout).append("\n");
            }
        }

        String summary = "Total calories burnt on " + date + ": " + caloriesBurnt;
        feedbackBuilder.append(summary);
        return new CommandResult(feedbackBuilder.toString());
    }

    @Override
    public void setArguments(String args) throws DateFormatException {
        date = Date.parseDate(args);
    }

    Calories getCaloriesBurnt() {
        return caloriesBurnt;
    }

    @Override
    protected String getHelp() {
        return HELP;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
// @@author
