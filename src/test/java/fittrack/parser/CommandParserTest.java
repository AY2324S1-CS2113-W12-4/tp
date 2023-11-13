package fittrack.parser;

import fittrack.command.AddMealCommand;
import fittrack.command.AddWorkoutCommand;
import fittrack.command.BmiCommand;
import fittrack.command.CaloriesConsumedCommand;
import fittrack.command.CaloriesBurntCommand;
import fittrack.command.CheckRecommendedWeightCommand;
import fittrack.command.Command;
import fittrack.command.CommandResult;
import fittrack.command.DeleteMealCommand;
import fittrack.command.DeleteWorkoutCommand;
import fittrack.command.EditProfileCommand;
import fittrack.command.ExitCommand;
import fittrack.command.FindMealCommand;
import fittrack.command.FindWorkoutCommand;
import fittrack.command.HelpCommand;
import fittrack.command.InvalidCommand;
import fittrack.command.ViewMealCommand;
import fittrack.command.ViewProfileCommand;
import fittrack.command.ViewWorkoutCommand;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CommandParserTest {

    @Test
    void parseCommand_emptyString_invalidCommand() {
        Command command = CommandParser.parseCommand("");
        assertInstanceOf(InvalidCommand.class, command);
    }

    @Test
    void parseCommand_help_helpCommand() {
        Command command = CommandParser.parseCommand("help");
        assertInstanceOf(HelpCommand.class, command);
        HelpCommand helpCommand = (HelpCommand) command;
        assertEquals(HelpCommand.HELP, helpCommand.getHelpMessage());
    }

    @Test
    void parseCommand_helpExit_helpCommandExit() {
        Command command = CommandParser.parseCommand("help exit");
        assertInstanceOf(HelpCommand.class, command);
        HelpCommand helpCommand = (HelpCommand) command;
        assertEquals(ExitCommand.HELP, helpCommand.getHelpMessage());
    }

    @Test
    void parseCommand_exit_exitCommand() {
        Command command = CommandParser.parseCommand("exit");
        assertInstanceOf(ExitCommand.class, command);
    }

    @Test
    void parseCommand_foo_invalidCommand() {
        Command command = CommandParser.parseCommand("foo");
        assertInstanceOf(InvalidCommand.class, command);
    }

    @Test
    void parseCommand_exitFoo_invalidCommand() {
        Command command = CommandParser.parseCommand("exit foo");
        assertInstanceOf(InvalidCommand.class, command);
    }

    @Test
    void getBlankCommand_help_helpCommand() {
        Command blankCommand = CommandParser.getBlankCommand("help", "help");
        assertInstanceOf(HelpCommand.class, blankCommand);
    }

    @Test
    void getBlankCommand_foo_invalidCommand() {
        Command blankCommand = CommandParser.getBlankCommand("foo", "foo");
        assertInstanceOf(InvalidCommand.class, blankCommand);
    }

    @Test
    void getBlankCommand_all_success() {
        getBlankCommandTest(HelpCommand.class, "help", null);
        getBlankCommandTest(ExitCommand.class, "exit", null);
        getBlankCommandTest(EditProfileCommand.class, "editprofile", "h/180 w/80 l/2000");
        getBlankCommandTest(ViewProfileCommand.class, "viewprofile", null);
        getBlankCommandTest(AddMealCommand.class, "addmeal", null);
        getBlankCommandTest(DeleteMealCommand.class, "deletemeal", null);
        getBlankCommandTest(ViewMealCommand.class, "viewmeal", null);
        getBlankCommandTest(AddWorkoutCommand.class, "addworkout", null);
        getBlankCommandTest(DeleteWorkoutCommand.class, "deleteworkout", null);
        getBlankCommandTest(ViewWorkoutCommand.class, "viewworkout", null);
        getBlankCommandTest(BmiCommand.class, "bmi", null);
        getBlankCommandTest(CaloriesConsumedCommand.class, "caloriesconsumed", null);
        getBlankCommandTest(CheckRecommendedWeightCommand.class, "checkrecommendedweight", null);
        getBlankCommandTest(CaloriesBurntCommand.class, "caloriesburnt", null);
        getBlankCommandTest(FindMealCommand.class, "findmeal", null);
        getBlankCommandTest(FindWorkoutCommand.class, "findworkout", null);
    }

    private void getBlankCommandTest(Class<? extends Command> expected, String word, String args) {
        String commandLine;
        if (args == null) {
            commandLine = word;
        } else {
            commandLine = word + " " + args;
        }
        assertInstanceOf(
                expected,
                CommandParser.getBlankCommand(word, commandLine)
        );
    }

    @Test
    void getInvalidCommandCommandResult_foo_foo() {
        CommandResult result = CommandParser
                .getInvalidCommandResult("exit foo", new PatternMatchFailException());
        assertEquals("`exit foo` is an invalid command. The input pattern is not valid.\n" +
                "`exit` halts the app.\n" +
                "Type `exit` to exit.", result.getFeedback());
    }









    @Test
    void parseIndex_one_success() {
        try {
            int idx = CommandParser.parseIndex("123");
            assertEquals(123, idx);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void parseIndex_fail() {
        assertThrows(AssertionError.class, () -> CommandParser.parseIndex(null));
        assertThrows(PatternMatchFailException.class, () -> CommandParser.parseIndex(""));
        assertThrows(NumberFormatException.class, () -> CommandParser.parseIndex("123 45"));
        assertThrows(NumberFormatException.class, () -> CommandParser.parseIndex("hi"));
        assertThrows(NumberFormatException.class, () -> CommandParser.parseIndex("01a"));
        assertThrows(NumberFormatException.class, () -> CommandParser.parseIndex("12.3"));
    }

    @Test
    void getFirstWord_helloWorld_hello() {
        String firstWord = CommandParser.getFirstWord("hello world");
        assertEquals("hello", firstWord);
    }

    @Test
    void getFirstWord_loremIpsum_lorem() {
        String firstWord = CommandParser.getFirstWord(
                "Lorem\nipsum\ndolor sit amet, consectetur adipisicing elit, \n" +
                        "sed do eiusmod tempor incididunt ut labore et dolore magna aliqua."
        );
        assertEquals("Lorem", firstWord);
    }

    @Test
    void getFirstWord_hi_hi() {
        String firstWord = CommandParser.getFirstWord("hi");
        assertEquals("hi", firstWord);
    }

    @Test
    void getFirstWord_emptyString_fail() {
        assertThrows(
                AssertionError.class,
                () -> CommandParser.getFirstWord("")
        );
    }
}
