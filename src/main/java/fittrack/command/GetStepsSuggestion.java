package fittrack.command;

import fittrack.data.Calories;
import fittrack.data.Date;
import fittrack.data.Step;
import fittrack.parser.ParseException;
import fittrack.storage.Storage;

public class GetStepsSuggestion extends Command{

    public static final String COMMAND_WORD = "getstepssuggestion";
    private static final String DESCRIPTION =
            String.format("`%s` checks if you are meeting your daily calorie goals through " +
                    "the number of steps walked.", COMMAND_WORD);
    private static final String USAGE = String.format("Type `%s` to check if you have met " +
                    "your daily calorie goals through the steps walked.\n" +
                    "Type `%s <DATE>` to check.\n" +
                    "You should type <DATE> in format of `yyyy-MM-dd`.",
            COMMAND_WORD, COMMAND_WORD
    );

    public static final String HELP = DESCRIPTION + "\n" + USAGE;

    private Date date;
    private Calories userCaloriesLimit;
    private final double caloriesPerStep = 0.04;
    private final Storage storage = new Storage();

    public GetStepsSuggestion(String commandLine) {
        super(commandLine);
    }

    public double calculateTotalCalories(int steps){
        return steps * caloriesPerStep;
    }

    public void setUserProfile() throws Storage.StorageOperationException {
        this.userCaloriesLimit = storage.loadProfile().getDailyCalorieLimit();
    }

    /**
     * Execute the command.
     *
     * @return result of the execution
     */
    @Override
    public CommandResult execute() {
        Step totalSteps = new Step(0, null);
        try {
            setUserProfile();
        } catch (Storage.StorageOperationException e) {
            throw new RuntimeException(e);
        }

        for (Step step: stepList.getStepList()) {
            if (date.equals(step.getDate())) {
                totalSteps = totalSteps.sum(step);
            }
        }

        double totalCaloriesBurned = calculateTotalCalories(totalSteps.getSteps());

        if (totalCaloriesBurned == 0){
            return new CommandResult("You have not walked any steps on this day.");
        } else if (totalCaloriesBurned > userCaloriesLimit.value) {
            return new CommandResult("You have exceeded your daily calorie limit. " +
                    "You can take a break.");
        } else if (totalCaloriesBurned < userCaloriesLimit.value) {
            return new CommandResult("You have not exceeded your daily calorie limit. " +
                    "You should walk " + (userCaloriesLimit.value - totalCaloriesBurned)/caloriesPerStep +
                    " more steps.");
        }
        return new CommandResult("Inconclusive data : (");
    }

    /**
     * Apply arguments to its field using parser.
     *
     * @param args arguments as a string
     * @throws ParseException if parse fails
     */
    @Override
    public void setArguments(String args) throws ParseException {
        date = Date.parseDate(args);
        date = Date.parseDate(args);
    }

    /**
     * Returns help of the command.
     *
     * @return help
     */
    @Override
    protected String getHelp() {
        return HELP;
    }
}
