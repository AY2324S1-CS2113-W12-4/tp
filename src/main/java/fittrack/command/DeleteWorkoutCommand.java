package fittrack.command;

import fittrack.parser.CommandParser;

public class DeleteWorkoutCommand extends Command {
    public static final String COMMAND_WORD = "deleteworkout";

    @Override
    public CommandResult execute() {
        return null;
    }

    @Override
    public void setArguments(String args, CommandParser parser) {
<<<<<<< Updated upstream

=======
        index = Integer.parseInt(args);
>>>>>>> Stashed changes
    }

    @Override
    protected String getHelp() {
        return null;
    }
}
