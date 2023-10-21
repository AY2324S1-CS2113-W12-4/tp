package fittrack.command;

import fittrack.data.Workout;
import fittrack.parser.CommandParser;
import fittrack.parser.NumberFormatException;
import fittrack.parser.PatternMatchFailException;

public class AddWorkoutCommand extends Command {
    public static final String COMMAND_WORD = "addworkout";
    private static final String DESCRIPTION =
            String.format("`%s` adds your daily workout data to the list.", COMMAND_WORD);
    private static final String USAGE = String.format(
            "Type `%s <WORKOUT_NAME> c/<CALORIES>` to add today's workout.\n" +
                    "Type `%s <WORKOUT_NAME> c/<CALORIES> d/<DATE>` to add a workout.\n" +
                    "You should type <DATE> in format of `yyyy-MM-dd`.",
            COMMAND_WORD, COMMAND_WORD
    );
    public static final String HELP = DESCRIPTION + "\n" + USAGE;

    private Workout newWorkout;

    @Override
    public CommandResult execute() {
        workoutList.addToList(newWorkout);
        return new CommandResult("I've added the following workout:" + "\n" + newWorkout.toString());
    }

    @Override
    public void setArguments(String args, CommandParser parser)
            throws PatternMatchFailException, NumberFormatException {
        newWorkout = parser.parseWorkout(args);
    }

    @Override
    protected String getHelp() {
        return HELP;
    }
}
