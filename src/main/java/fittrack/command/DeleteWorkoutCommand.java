package fittrack.command;

import fittrack.WorkoutList;
import fittrack.parser.CommandParser;

public class DeleteWorkoutCommand extends Command {
    public static final String COMMAND_WORD = "deleteworkout";

    private int index;

    @Override
    public CommandResult execute() {
        WorkoutList.deleteWorkout(index);
        return new CommandResult("I've deleted workout " + index);
    }

    @Override
    public void setArguments(String args, CommandParser parser) {
        String[] input = args.split(" ");
        index = Integer.parseInt(input[1]);
        System.out.println(index);
    }

    @Override
    protected String getHelp() {
        return null;
    }
}
