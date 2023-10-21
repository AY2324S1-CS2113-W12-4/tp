package fittrack.command;

import fittrack.parser.CommandParser;

public class InvalidCommand extends Command {
    public static final String MESSAGE_INVALID_COMMAND = "`%s` is an invalid command.";

    private String helpMessage;

    @Override
    public CommandResult execute() {
        return new CommandResult(helpMessage);
    }

    @Override
    public void setArguments(String inputLine, CommandParser parser) {
        HelpCommand helpCommand = new HelpCommand();
        helpCommand.setArguments(inputLine, parser);
        String message = helpCommand.execute().getFeedback();

        if (helpCommand.getCommandType() == InvalidCommand.class) {
            helpMessage = message;
        } else {
            helpMessage = String.format(MESSAGE_INVALID_COMMAND, inputLine) + "\n" + message;
        }

    }

    @Override
    protected String getHelp() {
        assert false;
        throw new UnsupportedOperationException();
    }
}
