package fittrack.command;

import fittrack.UserProfile;
import fittrack.parser.*;
import fittrack.parser.NumberFormatException;

public class EditProfileCommand extends Command {
    public static final String COMMAND_WORD = "editprofile";
    private static final String DESCRIPTION =
            String.format("`%s` allows you to edit your profile.", COMMAND_WORD);
    private static final String USAGE =
            String.format("Type `%s h/<HEIGHT> w/<WEIGHT> g/<GENDER> l/<CALORIE_LIMIT>` to edit.", COMMAND_WORD);
    public static final String HELP = DESCRIPTION + "\n" + USAGE;

    UserProfile newProfile;

    public EditProfileCommand(String commandLine) {
        super(commandLine);
    }

    @Override
    public CommandResult execute() {
        userProfile.setHeight(newProfile.getHeight());
        userProfile.setWeight(newProfile.getWeight());
        userProfile.setDailyCalorieLimit(newProfile.getDailyCalorieLimit());
        userProfile.setGender(newProfile.getGender());
        return new CommandResult("Here is your updated profile:\n" + userProfile.toString());
    }

    @Override
    public void setArguments(String args, CommandParser parser)
            throws PatternMatchFailException, NumberFormatException, NegativeNumberException, WrongGenderException {
        newProfile = parser.parseProfile(args);
    }

    @Override
    protected String getHelp() {
        return HELP;
    }
}
