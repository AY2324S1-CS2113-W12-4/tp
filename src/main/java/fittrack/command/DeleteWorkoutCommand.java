package fittrack.command;

import fittrack.WorkoutList;
import fittrack.parser.CommandParser;
import fittrack.parser.ParseException;

import java.util.Arrays;

public class DeleteWorkoutCommand extends Command {
    public static final String COMMAND_WORD = "deleteworkout";

    private int index;

    @Override
    public CommandResult execute() {
        WorkoutList.deleteWorkout(index);
        return new CommandResult("I've deleted workout " + index);
    }

    @Override
    public void setArguments(String args, CommandParser parser) throws ParseException {
        try {
            index = Integer.parseInt(args);
            if (index > WorkoutList.getWorkoutListSize()) {
                throw new ParseException("Index given is larger than array.");
            }
        } catch (NumberFormatException e) {
            throw new ParseException("Argument is not an integer.");
        }
    }

    @Override
    protected String getHelp() {
        return null;
    }
}
