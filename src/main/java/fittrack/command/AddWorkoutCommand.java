package fittrack.command;

import fittrack.Workout;
import fittrack.WorkoutList;
import fittrack.parser.CommandParser;

public class AddWorkoutCommand extends Command {
    public static final String COMMAND_WORD = "addworkout";
    private static final String DESCRIPTION =
            String.format("`%s` adds a workout to the list.", COMMAND_WORD);
    private static final String USAGE =
            String.format("Type `%s <workout> /cals <calories>` to add the workout to your list.", COMMAND_WORD);
    public static final String HELP = DESCRIPTION + "\n" + USAGE;

    private Workout newWorkout;

    @Override
    public CommandResult execute() {
        WorkoutList.addToList(newWorkout);
        return new CommandResult("I've added the following workout:" + "\n" + newWorkout.toString());
    }

    @Override
    public void setArguments(String args, CommandParser parser) {
        String[] input = args.split("/cals");
        newWorkout = new Workout(input[0], Float.parseFloat(input[1]));
    }

    @Override
    protected String getHelp() {
        return HELP;
    }
}
