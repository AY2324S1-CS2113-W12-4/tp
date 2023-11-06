package fittrack.command;


import fittrack.data.Date;
import fittrack.data.Meal;
import fittrack.data.Workout;
import fittrack.parser.CommandParser;
import fittrack.parser.ParseException;

public class CalorieBalanceCommand extends Command {
    public static final String COMMAND_WORD = "caloriebalance";
    private static final String DESCRIPTION =
            String.format("`%s` will take your calorie limit you set " +
                    "and will calculate your current calorie balance for" +
                    "the day using the calories you burnt during workouts" +
                    "and the calories you consumed during meals", COMMAND_WORD);
    private static final String USAGE = String.format(
            "Type `%s <DATE>` to add today's meal.\n" +
                    "Type `%s <MEAL_NAME> c/<CALORIES> d/<DATE>` to add a meal.\n" +
                    "You should type <DATE> in format of `yyyy-MM-dd`.",
            COMMAND_WORD, COMMAND_WORD
    );
    public static final String HELP = DESCRIPTION + "\n" + USAGE;

    private Date date;

    private double calorie_Balance;
    public CalorieBalanceCommand(String commandLine) {
        super(commandLine);
    }

    @Override
    public CommandResult execute() {
        calorie_Balance = userProfile.getDailyCalorieLimit().value;
        for(Workout workout: workoutList.getWorkoutList()) {
            if(date.equals(workout.getDate())) {
                calorie_Balance += workout.getCalories().value;
            }
        }

        for(Meal meal: mealList.getMealList()) {
            if(date.equals(meal.getDate())) {
                calorie_Balance -= meal.getCalories().value;
            }
        }
        if(calorie_Balance < 0) {
            return new CommandResult("You have exceeded your calorie limit on " + date +
                    " by: " + calorie_Balance + "kcal\n" +
                    "You are in a calorie surplus!\n" +
                    "Try doing more exercises if you want to eat!");
        } else if (calorie_Balance == 0) {
            return new CommandResult("Your calorie balance on " + date +
                    " is: " + calorie_Balance + "kcal\n"
                    + "Try doing more exercise if you want to eat!");
        } else {
            return new CommandResult("Your calorie balance on " + date +
                    " is: " + calorie_Balance + "kcal\n" +
                    "You are in a calorie deficit!\n" +
                    "You can try to eat more!");
        }
    }

    @Override
    public void setArguments(String args, CommandParser parser) throws ParseException {
        date = parser.parseDate(args);
    }

    @Override
    protected String getHelp() {
        return HELP;
    }
}
