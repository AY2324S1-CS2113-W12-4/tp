package fittrack.parser;

import fittrack.UserProfile;
import fittrack.command.Command;
import fittrack.command.ExitCommand;
import fittrack.command.HelpCommand;
import fittrack.command.InvalidCommand;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CommandParserTest {

    @Test
    void parseCommand_emptyString_invalidCommand() {
        Command command = new CommandParser().parseCommand("");
        assertInstanceOf(InvalidCommand.class, command);
    }

    @Test
    void parseCommand_help_helpCommand() {
        Command command = new CommandParser().parseCommand("help");
        assertInstanceOf(HelpCommand.class, command);
        HelpCommand helpCommand = (HelpCommand) command;
        assertEquals(HelpCommand.HELP, helpCommand.getHelpMessage());
    }

    @Test
    void parseCommand_helpExit_helpCommandExit() {
        Command command = new CommandParser().parseCommand("help exit");
        assertInstanceOf(HelpCommand.class, command);
        HelpCommand helpCommand = (HelpCommand) command;
        assertEquals(ExitCommand.HELP, helpCommand.getHelpMessage());
    }

    @Test
    void parseCommand_exit_exitCommand() {
        Command command = new CommandParser().parseCommand("exit");
        assertInstanceOf(ExitCommand.class, command);
    }

    @Test
    void parseCommand_foo_invalidCommand() {
        Command command = new CommandParser().parseCommand("foo");
        assertInstanceOf(InvalidCommand.class, command);
    }

    @Test
    void getBlankCommand_help_helpCommand() {
        Command blankCommand = new CommandParser().getBlankCommand("help");
        assertInstanceOf(HelpCommand.class, blankCommand);
    }

    @Test
    void getBlankCommand_foo_invalidCommand() {
        Command blankCommand = new CommandParser().getBlankCommand("foo");
        assertInstanceOf(InvalidCommand.class, blankCommand);
    }

    @Test
    void parseProfile_h180w80l2000_success() {
        try {
            UserProfile profile = new CommandParser().parseProfile("h/180 w/80 l/2000");
            assertEquals(180.0, profile.getHeight().value);
            assertEquals(80.0, profile.getWeight().value);
            assertEquals(2000.0, profile.getDailyCalorieLimit().value);
        } catch (PatternMatchFailException | NegativeNumberException | NumberFormatException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void parseProfile_fail() {
        CommandParser parser = new CommandParser();
        assertThrows(PatternMatchFailException.class, () -> parser.parseProfile("h/ w/ l/"));
        assertThrows(PatternMatchFailException.class, () -> parser.parseProfile("h/180 w/80 l/"));
        assertThrows(PatternMatchFailException.class, () -> parser.parseProfile("h/ w/80 l/2000"));
        assertThrows(PatternMatchFailException.class, () -> parser.parseProfile("h/180 80 2000"));
        assertThrows(PatternMatchFailException.class, () -> parser.parseProfile("180 w/80 l/2000"));
        assertThrows(PatternMatchFailException.class, () -> parser.parseProfile("180 80 2000"));
        assertThrows(NumberFormatException.class, () -> parser.parseProfile("h/180 w/eighty l/2000"));
        assertThrows(NegativeNumberException.class, () -> parser.parseProfile("h/-180 w/80 l/2000"));
    }

    @Test
    void getFirstWord_helloWorld_hello() {
        String firstWord = new CommandParser().getFirstWord("hello world");
        assertEquals("hello", firstWord);
    }

    @Test
    void getFirstWord_loremIpsum_lorem() {
        String firstWord = new CommandParser().getFirstWord(
                "Lorem\nipsum\ndolor sit amet, consectetur adipisicing elit, \n" +
                        "sed do eiusmod tempor incididunt ut labore et dolore magna aliqua."
        );
        assertEquals("Lorem", firstWord);
    }

    @Test
    void getFirstWord_hi_hi() {
        String firstWord = new CommandParser().getFirstWord("hi");
        assertEquals("hi", firstWord);
    }

    @Test
    void getFirstWord_emptyString_fail() {
        assertThrows(
                AssertionError.class,
                () -> new CommandParser().getFirstWord("")
        );
    }
}
