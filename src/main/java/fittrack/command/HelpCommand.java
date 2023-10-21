package fittrack.command;

import fittrack.parser.CommandParser;

import static fittrack.command.InvalidCommand.MESSAGE_INVALID_COMMAND;
import static fittrack.parser.CommandParser.ALL_COMMAND_WORDS;

public class HelpCommand extends Command {

    public static final String COMMAND_WORD = "help";
    private static final String DESCRIPTION =
            String.format("`%s` shows help message of the command.", COMMAND_WORD);
    private static final String KNOWN_COMMANDS = "Existing commands:\n" + ALL_COMMAND_WORDS;
    static final String USAGE =
            String.format("Type `%s` or `%s <COMMAND>` to view help.", COMMAND_WORD, COMMAND_WORD);
    public static final String HELP = DESCRIPTION + "\n" + KNOWN_COMMANDS + "\n" + USAGE;

    private String helpMessage;
    private Class<? extends Command> commandType;

    @Override
    public CommandResult execute() {
        return new CommandResult(helpMessage);
    }

    @Override
    public void setArguments(String args, CommandParser parser) {
        if (args.isEmpty()) {
            helpMessage = HELP;
            return;
        }

        String word = parser.getFirstWord(args);

        Command blankCommand = parser.getBlankCommand(word);
        commandType = blankCommand.getClass();

        if (blankCommand instanceof InvalidCommand) {
            helpMessage = String.format(MESSAGE_INVALID_COMMAND, word) + "\n" + USAGE;
            return;
        }

        helpMessage = blankCommand.getHelp();
    }

    @Override
    protected String getHelp() {
        return HELP;
    }

    public String getHelpMessage() {
        return helpMessage;
    }

    public Class<? extends Command> getCommandType() {
        return commandType;
    }
}
