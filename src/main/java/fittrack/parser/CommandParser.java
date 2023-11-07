package fittrack.parser;

import fittrack.UserProfile;
import fittrack.command.AddMealCommand;
import fittrack.command.AddStepsCommand;
import fittrack.command.AddWorkoutCommand;
import fittrack.command.BmiCommand;
import fittrack.command.CaloriesBurntCommand;
import fittrack.command.CaloriesConsumedCommand;
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
import fittrack.command.TotalStepsCommand;
import fittrack.command.ViewMealCommand;
import fittrack.command.ViewProfileCommand;
import fittrack.command.ViewStepsCommand;
import fittrack.command.ViewWorkoutCommand;

import fittrack.data.Date;
import fittrack.data.Step;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Represents the parser which parses commands.
 * <p>
 * Referenced
 * <a href="https://github.com/se-edu/addressbook-level2/blob/master/src/seedu/addressbook/parser/Parser.java">here</a>
 * to build main structure of this class.
 */
public class CommandParser {
    // This constant has to be changed whenever any command is added.
    public static final String ALL_COMMAND_WORDS = "help, exit,\n" +
            "editprofile, viewprofile, bmi, checkrecommendedweight,\n" +
            "addmeal, deletemeal, viewmeal, findmeal, caloriesconsumed,\n" +
            "addworkout, deleteworkout, viewworkout, findworkout, caloriesburnt";

    private static final String WORD_CG = "word";
    private static final String ARGS_CG = "args";
    private static final String HEIGHT_CG = "height";
    private static final String WEIGHT_CG = "weight";
    private static final String GENDER_CG = "gender";
    private static final String CAL_LIMIT_CG = "calLimit";
    private static final String NAME_CG = "name";
    private static final String CALORIES_CG = "calories";
    private static final String DATE_CG = "date";
    private static final String INDEX_CG = "index";
    private static final String KEYWORD_CG = "keyword";
    private static final String STEP_CG = "step";

    private static final Pattern COMMAND_PATTERN = Pattern.compile(
            "(?<" + WORD_CG + ">\\S+)(?<" + ARGS_CG + ">.*)"
    );
    private static final Pattern PROFILE_PATTERN = Pattern.compile(
            "h/(?<" + HEIGHT_CG + ">\\S+)\\s+w/(?<" + WEIGHT_CG +
                    ">\\S+)\\s+g/(?<" + GENDER_CG + ">\\S+)\\s+l/(?<" + CAL_LIMIT_CG + ">\\S+)"
    );
    private static final Pattern MEAL_PATTERN = Pattern.compile(
            "(?<" + NAME_CG + ">.+)\\s+c/(?<" + CALORIES_CG + ">\\S+)(\\s+d/(?<" + DATE_CG + ">\\S+))?"
    );
    private static final Pattern WORKOUT_PATTERN = Pattern.compile(
            "(?<" + NAME_CG + ">.+)\\s+c/(?<" + CALORIES_CG + ">\\S+)(\\s+d/(?<" + DATE_CG + ">\\S+))?"
    );
    private static final Pattern INDEX_PATTERN = Pattern.compile(
            "(?<" + INDEX_CG + ">\\S+)"
    );
    private static final Pattern DATE_PATTERN = Pattern.compile(
            "(?<" + DATE_CG + ">\\S+)"
    );
    private static final Pattern FIND_PATTERN = Pattern.compile(
            "(?<" + KEYWORD_CG + ">\\S+)"
    );
    private static final Pattern STEP_PATTERN = Pattern.compile(
            "(?<" + STEP_CG + ">\\S+)(\\s+d/(?<" + DATE_CG + ">\\S+))?"
    );

    public static Command parseCommand(String userCommandLine) {

        final Matcher matcher = COMMAND_PATTERN.matcher(userCommandLine.strip());
        if (!matcher.matches()) {
            return getInvalidCommand(userCommandLine);
        }

        final String word = matcher.group(WORD_CG).strip();
        final String args = matcher.group(ARGS_CG).strip();

        Command command = getBlankCommand(word, userCommandLine);
        if (command instanceof InvalidCommand) {
            return getInvalidCommand(userCommandLine);
        }
        try {
            command.setArguments(args);
        } catch (ParseException e) {
            return getInvalidCommand(userCommandLine, e);
        }

        return command;
    }

    public static Command getBlankCommand(String word, String commandLine) {
        switch (word) {
        case HelpCommand.COMMAND_WORD:
            return new HelpCommand(commandLine);
        case ExitCommand.COMMAND_WORD:
            return new ExitCommand(commandLine);
        case EditProfileCommand.COMMAND_WORD:
            return new EditProfileCommand(commandLine);
        case ViewProfileCommand.COMMAND_WORD:
            return new ViewProfileCommand(commandLine);
        case AddMealCommand.COMMAND_WORD:
            return new AddMealCommand(commandLine);
        case DeleteMealCommand.COMMAND_WORD:
            return new DeleteMealCommand(commandLine);
        case ViewMealCommand.COMMAND_WORD:
            return new ViewMealCommand(commandLine);
        case AddWorkoutCommand.COMMAND_WORD:
            return new AddWorkoutCommand(commandLine);
        case DeleteWorkoutCommand.COMMAND_WORD:
            return new DeleteWorkoutCommand(commandLine);
        case ViewWorkoutCommand.COMMAND_WORD:
            return new ViewWorkoutCommand(commandLine);
        case BmiCommand.COMMAND_WORD:
            return new BmiCommand(commandLine);
        case CaloriesConsumedCommand.COMMAND_WORD:
            return new CaloriesConsumedCommand(commandLine);
        case CheckRecommendedWeightCommand.COMMAND_WORD:
            return new CheckRecommendedWeightCommand(commandLine);
        case CaloriesBurntCommand.COMMAND_WORD:
            return new CaloriesBurntCommand(commandLine);
        case FindMealCommand.COMMAND_WORD:
            return new FindMealCommand(commandLine);
        case FindWorkoutCommand.COMMAND_WORD:
            return new FindWorkoutCommand(commandLine);
        case AddStepsCommand.COMMAND_WORD:
            return new AddStepsCommand(commandLine);
        case TotalStepsCommand.COMMAND_WORD:
            return new TotalStepsCommand(commandLine);
        case ViewStepsCommand.COMMAND_WORD:
            return new ViewStepsCommand(commandLine);
        default:
            return new InvalidCommand(commandLine);

        }
    }

    public static InvalidCommand getInvalidCommand(String userCommandLine) {
        return getInvalidCommand(userCommandLine, null);
    }

    public static InvalidCommand getInvalidCommand(String userCommandLine, ParseException e) {
        InvalidCommand invalidCommand = new InvalidCommand(userCommandLine, e);
        invalidCommand.setArguments(userCommandLine);
        return invalidCommand;
    }

    public static CommandResult getInvalidCommandResult(String userCommandLine, ParseException e) {
        return getInvalidCommand(userCommandLine, e).execute();
    }

    // @@author NgLixuanNixon
    public static int parseIndex(String args) throws ParseException {
        assert args != null;
        String index = args.strip();

        if (index.isEmpty()) {
            throw new PatternMatchFailException();
        }
        try {
            int idx = Integer.parseInt(index);
            if (idx <= 0) {
                throw IndexOutOfBoundsException.INDEX_INVALID;
            }
            return idx;
        } catch (java.lang.NumberFormatException e) {
            throw new NumberFormatException("Index must be an integer.");
        }
    }
    // @@author

    // @@author J0shuaLeong
    public static String parseKeyword(String args) throws PatternMatchFailException {
        assert args != null;
        String keyword = args.strip();

        if (keyword.isEmpty()) {
            throw new PatternMatchFailException();
        }
        return keyword;
    }
    // @@author

    public static String getFirstWord(String str) {
        assert str != null && !str.isEmpty();
        return str.split("\\s")[0];
    }

    public Step parseStep(String steps) throws PatternMatchFailException, NumberFormatException {

        final Matcher matcher = STEP_PATTERN.matcher(steps);
        if (!matcher.matches()) {
            throw new PatternMatchFailException();
        }

        final String step = matcher.group(STEP_CG);
        final String date = matcher.group(DATE_CG);

        try {
            return new Step(Integer.parseInt(step), new Date(date));
        } catch (java.lang.NumberFormatException e) {
            throw new NumberFormatException(e.getMessage());
        }
    }
}
