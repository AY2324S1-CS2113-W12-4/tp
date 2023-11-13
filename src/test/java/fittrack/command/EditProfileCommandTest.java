package fittrack.command;

import fittrack.UserProfile;
import fittrack.parser.PatternMatchFailException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class EditProfileCommandTest {
    private EditProfileCommand editProfileCommand;
    private UserProfile userProfile;

    @BeforeEach
    public void setUp() {
        editProfileCommand = new EditProfileCommand(EditProfileCommand.COMMAND_WORD);
        userProfile = new UserProfile();
        editProfileCommand.setData(userProfile, null, null, null);
    }

    @Test
    public void setArgumentsInvalidArgumentsThrowsException() {
        String invalidArgs = "invalid_arguments";

        assertThrows(PatternMatchFailException.class, () -> {
            editProfileCommand.setArguments(invalidArgs);
        });
    }

    @Test
    public void getHelpReturnsCorrectHelpMessage() {
        EditProfileCommand editProfileCommand = new EditProfileCommand(EditProfileCommand.COMMAND_WORD);

        String expectedHelpMessage = "`editprofile` allows you to edit your profile." +
                "\nType `editprofile h/<HEIGHT> w/<WEIGHT> g/<GENDER> l/<CALORIE_LIMIT>` to edit.";

        String actualHelpMessage = editProfileCommand.getHelp();

        assertEquals(expectedHelpMessage, actualHelpMessage);
    }
}
