package fittrack.parser;

import fittrack.UserProfile;
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
import fittrack.command.SaveCommand;
import fittrack.command.ViewMealCommand;
import fittrack.command.ViewProfileCommand;
import fittrack.command.ViewWorkoutCommand;
import fittrack.data.Date;
import fittrack.data.Meal;
import fittrack.data.Workout;
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
    void parseCommand_exitFoo_invalidCommand() {
        Command command = new CommandParser().parseCommand("exit foo");
        assertInstanceOf(InvalidCommand.class, command);
    }

    @Test
    void getBlankCommand_help_helpCommand() {
        Command blankCommand = new CommandParser().getBlankCommand("help", "help");
        assertInstanceOf(HelpCommand.class, blankCommand);
    }

    @Test
    void getBlankCommand_foo_invalidCommand() {
        Command blankCommand = new CommandParser().getBlankCommand("foo", "foo");
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
        getBlankCommandTest(SaveCommand.class, "save", null);
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
                new CommandParser().getBlankCommand(word, commandLine)
        );
    }

    @Test
    void getInvalidCommandCommandResult_foo_foo() {
        CommandResult result = new CommandParser()
                .getInvalidCommandResult("exit foo", new PatternMatchFailException());
        assertEquals("`exit foo` is an invalid command.\n" +
                "`exit` makes you to exit this program.\n" +
                "Type `exit` to exit.", result.getFeedback());
    }

    @Test
    void parseProfile_h180w80l2000_success() {
        try {
            UserProfile profile = new CommandParser().parseProfile("h/180 w/80 g/M l/2000");
            assertEquals(180.0, profile.getHeight().value);
            assertEquals(80.0, profile.getWeight().value);
            assertEquals('M', profile.getGender().getGender());
            assertEquals(2000.0, profile.getDailyCalorieLimit().value);
        } catch (PatternMatchFailException | NegativeNumberException | NumberFormatException | WrongGenderException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void parseProfile_fail() {
        CommandParser parser = new CommandParser();
        assertThrows(PatternMatchFailException.class, () -> parser.parseProfile(""));
        assertThrows(PatternMatchFailException.class, () -> parser.parseProfile("h/ w/ g/ l/"));
        assertThrows(PatternMatchFailException.class, () -> parser.parseProfile("h/180 w/80 g/ l/"));
        assertThrows(PatternMatchFailException.class, () -> parser.parseProfile("h/ w/80 g/ l/2000"));
        assertThrows(PatternMatchFailException.class, () -> parser.parseProfile("h/180 80 M 2000"));
        assertThrows(PatternMatchFailException.class, () -> parser.parseProfile("180 w/80 g/M l/2000"));
        assertThrows(PatternMatchFailException.class, () -> parser.parseProfile("180 80 M 2000"));
        assertThrows(NumberFormatException.class, () -> parser.parseProfile("h/180 w/eighty g/M l/2000"));
        assertThrows(NegativeNumberException.class, () -> parser.parseProfile("h/-180 w/80 g/M l/2000"));
    }

    @Test
    void parseMeal_nc12345_success() {
        try {
            Meal meal = new CommandParser().parseMeal("name c/123.45");
            assertEquals("name", meal.getName());
            assertEquals(123.45, meal.getCalories().value);
            assertEquals(Date.today(), meal.getDate());
        } catch (PatternMatchFailException | NumberFormatException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void parseMeal_nc12345d20231031_success() {
        try {
            Meal meal = new CommandParser().parseMeal("name c/123.45 d/2023-10-31");
            assertEquals("name", meal.getName());
            assertEquals(123.45, meal.getCalories().value);
            assertEquals(new Date(2023, 10, 31), meal.getDate());
        } catch (PatternMatchFailException | NumberFormatException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void parseMeal_fail() {
        CommandParser parser = new CommandParser();
        assertThrows(PatternMatchFailException.class, () -> parser.parseMeal(""));
        assertThrows(PatternMatchFailException.class, () -> parser.parseMeal("namec/123d/2023-10-31"));
        assertThrows(PatternMatchFailException.class, () -> parser.parseMeal("name"));
        assertThrows(PatternMatchFailException.class, () -> parser.parseMeal("name c/"));
        assertThrows(PatternMatchFailException.class, () -> parser.parseMeal("name c/123 d/"));
        assertThrows(PatternMatchFailException.class, () -> parser.parseMeal("c/123 d/2023-10-31"));
        assertThrows(PatternMatchFailException.class, () -> parser.parseMeal("name c/100 d/oct31"));
        assertThrows(NumberFormatException.class, () -> parser.parseMeal("name c/hundred"));
    }

    @Test
    void parseWorkout_nc12345_success() {
        try {
            Workout workout = new CommandParser().parseWorkout("name c/123.45");
            assertEquals("name", workout.getName());
            assertEquals(123.45, workout.getCalories().value);
            assertEquals(Date.today(), workout.getDate());
        } catch (PatternMatchFailException | NumberFormatException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void parseWorkout_nc12345d20231031_success() {
        try {
            Workout workout = new CommandParser().parseWorkout("name c/123.45 d/2023-10-31");
            assertEquals("name", workout.getName());
            assertEquals(123.45, workout.getCalories().value);
            assertEquals(new Date(2023, 10, 31), workout.getDate());
        } catch (PatternMatchFailException | NumberFormatException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void parseWorkout_fail() {
        CommandParser parser = new CommandParser();
        assertThrows(PatternMatchFailException.class, () -> parser.parseWorkout(""));
        assertThrows(PatternMatchFailException.class, () -> parser.parseWorkout("namec/123d/2023-10-31"));
        assertThrows(PatternMatchFailException.class, () -> parser.parseWorkout("name"));
        assertThrows(PatternMatchFailException.class, () -> parser.parseWorkout("name c/"));
        assertThrows(PatternMatchFailException.class, () -> parser.parseWorkout("name c/123 d/"));
        assertThrows(PatternMatchFailException.class, () -> parser.parseWorkout("c/123 d/2023-10-31"));
        assertThrows(PatternMatchFailException.class, () -> parser.parseWorkout("name c/100 d/oct31"));
        assertThrows(NumberFormatException.class, () -> parser.parseWorkout("name c/hundred"));
    }

    @Test
    void parseIndex_one_success() {
        try {
            int idx = new CommandParser().parseIndex("123");
            assertEquals(123, idx);
        } catch (PatternMatchFailException | NumberFormatException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void parseIndex_fail() {
        CommandParser parser = new CommandParser();
        assertThrows(PatternMatchFailException.class, () -> parser.parseIndex(""));
        assertThrows(PatternMatchFailException.class, () -> parser.parseIndex("123 45"));
        assertThrows(NumberFormatException.class, () -> parser.parseIndex("hi"));
        assertThrows(NumberFormatException.class, () -> parser.parseIndex("01a"));
        assertThrows(NumberFormatException.class, () -> parser.parseIndex("12.3"));
    }

    @Test
    void parseDate_date20231031_success() {
        try {
            Date date = new CommandParser().parseDate("2023-10-31");
            assertEquals(new Date(2023, 10, 31), date);
        } catch (PatternMatchFailException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void parseDate_fail() {
        CommandParser parser = new CommandParser();
        assertThrows(PatternMatchFailException.class, () -> parser.parseDate(""));
        assertThrows(PatternMatchFailException.class, () -> parser.parseDate("10-31"));
        assertThrows(PatternMatchFailException.class, () -> parser.parseDate("Oct 31"));
        assertThrows(PatternMatchFailException.class, () -> parser.parseDate("10.31."));
    }

    @Test
    void parseFind_key_success() {
        try {
            String keyword = new CommandParser().parseFind("key");
            assertEquals("key", keyword);
        } catch (PatternMatchFailException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void parseFind_fail() {
        CommandParser parser = new CommandParser();
        assertThrows(PatternMatchFailException.class, () -> parser.parseFind(""));
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
