package fittrack.parser;

import fittrack.Meal;
import fittrack.UserProfile;
import fittrack.Workout;
import fittrack.command.AddMealCommand;
import fittrack.command.AddWorkoutCommand;
import fittrack.command.Command;
import fittrack.command.DeleteMealCommand;
import fittrack.command.DeleteWorkoutCommand;
import fittrack.command.EditProfileCommand;
import fittrack.command.ExitCommand;
import fittrack.command.HelpCommand;
import fittrack.command.InvalidCommand;
import fittrack.command.ViewMealsCommand;
import fittrack.command.ViewWorkoutsCommand;
import fittrack.command.ViewProfileCommand;

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
    public static final String ALL_COMMAND_WORDS = "help, exit, " +
            "editprofile, viewprofile, " +
            "addmeal, deletemeal, viewmeals, " +
            "addworkout, deleteworkout, viewworkouts";
  
    private static final Pattern COMMAND_PATTERN = Pattern.compile(
            "(?<word>\\S+)(?<args>.*)"
    );
    private static final Pattern PROFILE_PATTERN = Pattern.compile(
            "h/(?<height>\\S+)\\s+w/(?<weight>\\S+)\\s+l/(?<calLimit>\\S+)"
    );
    private static final Pattern MEAL_PATTERN = Pattern.compile(
            "(?<name>\\S+)\\s+c/(?<calories>\\S+)"
    );
    private static final Pattern WORKOUT_PATTERN = Pattern.compile(
            "(?<name>\\S+)\\s+c/(?<calories>\\S+)"
    );

    public Command parseCommand(String userCommandLine) {
        final Matcher matcher = COMMAND_PATTERN.matcher(userCommandLine.strip());
        if (!matcher.matches()) {
            return getInvalidCommand(userCommandLine);
        }

        final String word = matcher.group("word").strip();
        final String args = matcher.group("args").strip();

        Command command = getBlankCommand(word);
        try {
            command.setArguments(args, this);
        } catch (ParseException e) {
            return getInvalidCommand(userCommandLine);
        }

        return command;
    }

    public Command getBlankCommand(String word) {
        switch (word) {

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();
        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();
        case EditProfileCommand.COMMAND_WORD:
            return new EditProfileCommand();
        case ViewProfileCommand.COMMAND_WORD:
            return new ViewProfileCommand();
        case AddMealCommand.COMMAND_WORD:
            return new AddMealCommand();
        case DeleteMealCommand.COMMAND_WORD:
            return new DeleteMealCommand();
        case ViewMealsCommand.COMMAND_WORD:
            return new ViewMealsCommand();
        case AddWorkoutCommand.COMMAND_WORD:
            return new AddWorkoutCommand();
        case DeleteWorkoutCommand.COMMAND_WORD:
            return new DeleteWorkoutCommand();
        case ViewWorkoutsCommand.COMMAND_WORD:
            return new ViewWorkoutsCommand();
        default:
            return new InvalidCommand(word);

        }
    }

    private InvalidCommand getInvalidCommand(String userCommandLine) {
        InvalidCommand invalidCommand = new InvalidCommand(userCommandLine);
        invalidCommand.setArguments(null, this);
        return invalidCommand;
    }

    /**
     * Parses user profile, format of `h/(HEIGHT) w/(WEIGHT)`.
     *
     * @param profile profile as a string
     * @return height and weight as a double array
     * @throws PatternMatchFailException if regex match fails
     * @throws NumberFormatException if one of arguments is not double
     */
    public UserProfile parseProfile(String profile) throws PatternMatchFailException, NumberFormatException {
        final Matcher matcher = PROFILE_PATTERN.matcher(profile);
        if (!matcher.matches()) {
            throw new PatternMatchFailException();
        }

        final String height = matcher.group("height");
        final String weight = matcher.group("weight");
        final String dailyCalorieLimit = matcher.group("calLimit");

        try {
            return new UserProfile(
                    Double.parseDouble(height),
                    Double.parseDouble(weight),
                    Double.parseDouble(dailyCalorieLimit)
            );
        } catch (java.lang.NumberFormatException e) {
            throw new NumberFormatException();
        }
    }

    public Meal parseMeal(String meal) throws PatternMatchFailException, NumberFormatException {
        final Matcher matcher = MEAL_PATTERN.matcher(meal);
        if (!matcher.matches()) {
            throw new PatternMatchFailException();
        }

        final String name = matcher.group("name");
        final String calories = matcher.group("calories");

        try {
            return new Meal(name, Double.parseDouble(calories));
        } catch (java.lang.NumberFormatException e) {
            throw new NumberFormatException();
        }
    }

    public Workout parseWorkout(String workout) throws PatternMatchFailException, NumberFormatException {
        final Matcher matcher = WORKOUT_PATTERN.matcher(workout);
        if (!matcher.matches()) {
            throw new PatternMatchFailException();
        }

        final String name = matcher.group("name");
        final String calories = matcher.group("calories");

        try {
            return new Workout(name, Double.parseDouble(calories));
        } catch (java.lang.NumberFormatException e) {
            throw new NumberFormatException();
        }
    }

    public String getFirstWord(String str) {
        assert str != null && !str.isEmpty();
        return str.split("\\s")[0];
    }
}
