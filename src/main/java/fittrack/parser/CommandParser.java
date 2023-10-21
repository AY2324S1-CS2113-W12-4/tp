package fittrack.parser;

import fittrack.data.Meal;
import fittrack.UserProfile;
import fittrack.data.Workout;
import fittrack.data.Calories;
import fittrack.data.Date;
import fittrack.data.Height;
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
import fittrack.command.BmiCommand;
import fittrack.command.SaveCommand;
import fittrack.data.Weight;

import java.time.format.DateTimeParseException;
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
            "addworkout, deleteworkout, viewworkouts, bmi, save";
  
    private static final Pattern COMMAND_PATTERN = Pattern.compile(
            "(?<word>\\S+)(?<args>.*)"
    );
    private static final Pattern PROFILE_PATTERN = Pattern.compile(
            "h/(?<height>\\S+)\\s+w/(?<weight>\\S+)\\s+l/(?<calLimit>\\S+)"
    );
    private static final Pattern MEAL_PATTERN = Pattern.compile(
            "(?<name>.+)\\s+c/(?<calories>\\S+)(\\s+d/(?<date>\\S+))?"
    );
    private static final Pattern WORKOUT_PATTERN = Pattern.compile(
            "(?<name>.+)\\s+c/(?<calories>\\S+)(\\s+d/(?<date>\\S+))?"
    );

    public Command parseCommand(String userCommandLine) {
        final Matcher matcher = COMMAND_PATTERN.matcher(userCommandLine.strip());
        if (!matcher.matches()) {
            return getInvalidCommand(userCommandLine);
        }

        final String word = matcher.group("word").strip();
        final String args = matcher.group("args").strip();

        Command command = getBlankCommand(word);
        if (command instanceof InvalidCommand) {
            return getInvalidCommand(userCommandLine);
        }
        try {
            command.setArguments(args, this);
        } catch (ParseException e) {
            System.out.println(e.getMessage());
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
        case BmiCommand.COMMAND_WORD:
            return new BmiCommand();
        case SaveCommand.COMMAND_WORD:
            return new SaveCommand();
        default:
            return new InvalidCommand();
        }
    }

    private InvalidCommand getInvalidCommand(String userCommandLine) {
        InvalidCommand invalidCommand = new InvalidCommand();
        invalidCommand.setArguments(userCommandLine, this);
        return invalidCommand;
    }

    /**
     * Parses user profile, format of `h/(HEIGHT) w/(WEIGHT) l/(CALORIES)`.
     *
     * @param profile profile as a string
     * @return height and weight as a double array
     * @throws PatternMatchFailException if regex match fails
     * @throws NumberFormatException if one of arguments is not double
     */
    public UserProfile parseProfile(String profile)
            throws PatternMatchFailException, NumberFormatException, NegativeNumberException {
        final Matcher matcher = PROFILE_PATTERN.matcher(profile);
        if (!matcher.matches()) {
            throw new PatternMatchFailException();
        }

        try {
            final double height = Double.parseDouble(matcher.group("height"));
            final double weight = Double.parseDouble(matcher.group("weight"));
            final double dailyCalorieLimit = Double.parseDouble(matcher.group("calLimit"));

            // Height, weight and calories cannot be negative. Throw exception if it happens
            if (height < 0 || weight < 0 || dailyCalorieLimit < 0) {
                throw new NegativeNumberException();
            }

            Height heightData = new Height(height);
            Weight weightData = new Weight(weight);
            Calories caloriesData = new Calories(dailyCalorieLimit);

            return new UserProfile(heightData, weightData, caloriesData);
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
        final String date = matcher.group("date");

        try {
            double caloriesInDouble = Double.parseDouble(calories);

            if (date == null) {
                return new Meal(name, new Calories(caloriesInDouble), Date.today());
            } else {
                return new Meal(name, new Calories(caloriesInDouble), new Date(date));
            }
        } catch (java.lang.NumberFormatException e) {
            throw new NumberFormatException();
        } catch (DateTimeParseException e) {
            throw new PatternMatchFailException();
        }
    }

    public Workout parseWorkout(String workout) throws PatternMatchFailException, NumberFormatException {
        final Matcher matcher = WORKOUT_PATTERN.matcher(workout);
        if (!matcher.matches()) {
            throw new PatternMatchFailException();
        }

        final String name = matcher.group("name");
        final String calories = matcher.group("calories");
        final String date = matcher.group("date");

        try {
            double caloriesInDouble = Double.parseDouble(calories);

            if (date == null) {
                return new Workout(name, new Calories(caloriesInDouble), Date.today());
            } else {
                return new Workout(name, new Calories(caloriesInDouble), new Date(date));
            }
        } catch (java.lang.NumberFormatException e) {
            throw new NumberFormatException();
        } catch (DateTimeParseException e) {
            throw new PatternMatchFailException();
        }
    }

    public String getFirstWord(String str) {
        assert str != null && !str.isEmpty();
        return str.split("\\s")[0];
    }
}
