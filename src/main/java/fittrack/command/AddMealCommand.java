package fittrack.command;

import fittrack.parser.CommandParser;

public class AddMealCommand extends Command {
    public static final String COMMAND_WORD = "addmeal";

    @Override
    public CommandResult execute() {
        return null;
    }

    @Override
    public void setArguments(String args, CommandParser parser) {

    }

    @Override
    protected String getHelp() {
        return null;
    }
}
