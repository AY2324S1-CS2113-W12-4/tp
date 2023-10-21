package fittrack.command;

import fittrack.data.Workout;
import fittrack.parser.CommandParser;
import fittrack.parser.ParseException;

public class DeleteWorkoutCommand extends Command {
    public static final String COMMAND_WORD = "deleteworkout";
    private static final String DESCRIPTION =
            String.format("`%s` deletes your daily workout data from the list.", COMMAND_WORD);
    private static final String USAGE =
            String.format("Type `%s <INDEX>` to delete the workout by an index.", COMMAND_WORD);
    public static final String HELP = DESCRIPTION + "\n" + USAGE;

    private int workoutIndex;

    @Override
    public CommandResult execute() {
        Workout toDelete = workoutList.getWorkout(workoutIndex);
        workoutList.deleteWorkout(workoutIndex);
        return new CommandResult("I've deleted the following workout:" + "\n" + toDelete.toString());
    }

    @Override
    public void setArguments(String args, CommandParser parser) throws ParseException {
        try {
            workoutIndex = Integer.parseInt(args);
            if (workoutIndex > workoutList.getWorkoutListSize()) {
                throw new ParseException("Index given is larger than array.");
            }
        } catch (NumberFormatException e) {
            throw new ParseException("Argument is not an integer.");
        }
    }

    @Override
    protected String getHelp() {
        return HELP;
    }
}
