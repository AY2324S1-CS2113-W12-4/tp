package fittrack.command;

import fittrack.parser.CommandParser;
import org.junit.jupiter.api.Test;

import static fittrack.command.HelpCommand.USAGE;
import static fittrack.command.InvalidCommand.MESSAGE_INVALID_COMMAND;
import static org.junit.jupiter.api.Assertions.assertEquals;

class HelpCommandTest {

    @Test
    void execute_help_pass() throws Storage.StorageOperationException {
        HelpCommand helpCommand = new HelpCommand("help");
        helpCommand.setArguments("", new CommandParser());
        CommandResult result = helpCommand.execute();
        assertEquals(HelpCommand.HELP, result.getFeedback());
    }

    @Test
    void setArguments_emptyString_helpOfHelp() throws Storage.StorageOperationException {
        HelpCommand helpCommand = new HelpCommand("help");
        helpCommand.setArguments("", new CommandParser());
        assertEquals(HelpCommand.HELP, helpCommand.getHelpMessage());
    }

    @Test
    void setArguments_help_helpOfHelp() throws Storage.StorageOperationException {
        HelpCommand helpCommand = new HelpCommand("help help");
        helpCommand.setArguments("help", new CommandParser());
        assertEquals(HelpCommand.HELP, helpCommand.getHelpMessage());
    }

    @Test
    void setArguments_exit_helpOfExit() throws Storage.StorageOperationException {
        HelpCommand helpCommand = new HelpCommand("help exit");
        helpCommand.setArguments("exit", new CommandParser());
        assertEquals(ExitCommand.HELP, helpCommand.getHelpMessage());
    }

    @Test
    void setArguments_foo_invalidCmdMsg() throws Storage.StorageOperationException {
        HelpCommand helpCommand = new HelpCommand("help foo");
        helpCommand.setArguments("foo", new CommandParser());
        assertEquals(
                String.format(MESSAGE_INVALID_COMMAND, "foo") + "\n" + USAGE, 
                helpCommand.getHelpMessage()
        );
    }
}
