package fittrack.command;


import fittrack.data.Date;
import fittrack.data.Meal;
import fittrack.data.Workout;
import fittrack.parser.ParseException;

public class CalorieBalanceCommand extends Command {
    public static final String COMMAND_WORD = "caloriebalance";
    private static final String DESCRIPTION =
            String.format("`%s` will take your calorie limit you set " +
                    "and will calculate your current calorie balance for " +
                    "the day using the calories you burnt during workouts" +
                    "and the calories you consumed during meals", COMMAND_WORD);
    private static final String USAGE =
            String.format("Type `%s <DATE>` to see today's calorie balance.\n" +
                    "You should type <DATE> in format of `yyyy-MM-dd`.",
                    COMMAND_WORD);

    public static final String HELP = DESCRIPTION + "\n" + USAGE;

    private Date date;

    private double calorieBalance;
    public CalorieBalanceCommand(String commandLine) {
        super(commandLine);
    }

    @Override
    public CommandResult execute() {
        calorieBalance = userProfile.getDailyCalorieLimit().value;
        for(Workout workout: workoutList.getWorkoutList()) {
            if(date.equals(workout.getDate())) {
                calorieBalance += workout.getCalories().value;
            }
        }

        for(Meal meal: mealList.getMealList()) {
            if(date.equals(meal.getDate())) {
                calorieBalance -= meal.getCalories().value;
            }
        }
        if(calorieBalance < 0) {
            return new CommandResult("You have exceeded your calorie limit on " + date +
                    " by: " + (calorieBalance * -1) + "kcal\n" +
                    "You are in a calorie surplus!\n" +
                    "Try doing more exercises if you want to eat!");
        } else if (calorieBalance == 0) {
            return new CommandResult("Your calorie balance on " + date +
                    " is: " + calorieBalance + "kcal\n"
                    + "Try doing more exercise if you want to eat!");
        } else {
            return new CommandResult("Your calorie balance on " + date +
                    " is: " + calorieBalance + "kcal\n" +
                    "You are in a calorie deficit!\n" +
                    "You can try to eat more!");
        }
    }

    @Override
    public void setArguments(String args) throws ParseException {
        date = Date.parseDate(args);
    }

    @Override
    protected String getHelp() {
        return HELP;
    }
}
