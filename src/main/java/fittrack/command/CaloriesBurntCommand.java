package fittrack.command;

import fittrack.data.Date;
import fittrack.data.Workout;
import fittrack.parser.CommandParser;
import fittrack.parser.ParseException;

public class CaloriesBurntCommand extends Command {
    public static final String COMMAND_WORD = "caloriesburnt";
    private static final String DESCRIPTION =
            String.format("`%s` shows your total calories burnt on a specific date", COMMAND_WORD);
    private static final String USAGE = String.format(
            "Type `%s <DATE>` to see the total calories burnt on that date.\n" +
                    "You should type <DATE> in format of `yyyy-MM-dd`.",
            COMMAND_WORD
    );
    public static final String HELP = DESCRIPTION + "\n" + USAGE;
    private Date date;
    private double caloriesBurnt;
    public CaloriesBurntCommand(String commandLine) {
        super(commandLine);
    }

    @Override
    public CommandResult execute() {
        for(Workout workout: workoutList.getWorkoutList()) {
            if(date.equals(workout.getDate())) {
                caloriesBurnt += workout.getCalories();
                System.out.println(workout.toString());
            }
        }
        return new CommandResult("Total calories burnt on " + date +
                ": " + caloriesBurnt + "cals");
    }

    @Override
    public void setArguments(String args, CommandParser parser) throws ParseException {
        date = parser.parseDate(args);
    }

    @Override
    protected String getHelp() {
        return HELP;
    }

    public double getCaloriesBurnt() {
        return this.caloriesBurnt;
    }
}
