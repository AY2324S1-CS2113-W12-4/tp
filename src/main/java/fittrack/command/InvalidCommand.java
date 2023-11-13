package fittrack.command;

import fittrack.parser.ParseException;

public class InvalidCommand extends Command {
    public static final String MESSAGE_INVALID_COMMAND = "`%s` is an invalid command.";

    private String helpMessage;
    private final String exceptionMessage;

    public InvalidCommand(String commandLine) {
        this(commandLine, null);
    }

    public InvalidCommand(String commandLine, ParseException e) {
        super(commandLine);
        if (e != null && e.getMessage() != null) {
            this.exceptionMessage = e.getMessage();
        } else {
            this.exceptionMessage = null;
        }
    }

    @Override
    public CommandResult execute() {
        return new CommandResult(helpMessage);
    }


    public void setArguments(String inputLine) {
        HelpCommand helpCommand = new HelpCommand(inputLine);
        helpCommand.setArguments(inputLine.strip());
        String message = helpCommand.execute().getFeedback();

        if (helpCommand.getCommandType() == null) {
            helpMessage = getInvalidCommandMessage(inputLine) + "\n" + HelpCommand.USAGE;
        } else if (helpCommand.getCommandType() == InvalidCommand.class) {
            helpMessage = message;
        } else {
            String invalidCommandMessage = getInvalidCommandMessage(inputLine);
            if (exceptionMessage != null) {
                invalidCommandMessage += " " + this.exceptionMessage;
            }
            helpMessage = invalidCommandMessage + "\n" + message;
        }
    }

    @Override
    protected String getHelp() {
        assert false;
        throw new UnsupportedOperationException();
    }

    static String getInvalidCommandMessage(String line) {
        return String.format(MESSAGE_INVALID_COMMAND, line);
    }
}
