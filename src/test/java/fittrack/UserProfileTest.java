package fittrack;

import fittrack.data.Calories;
import fittrack.data.Gender;
import fittrack.data.Height;
import fittrack.data.Weight;
import fittrack.parser.IllegalValueException;
import fittrack.parser.NumberFormatException;
import fittrack.parser.ParseException;
import fittrack.parser.PatternMatchFailException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class UserProfileTest {

    @Test
    void getBmi() {
        // TODO: check if setXXX() updates BMI.
    }

    @Test
    void toString_success() {
        assertEquals(
                "Height: 187.2cm\n" +
                "Weight: 76.8kg\n" +
                "Gender: Male\n" +
                "Daily calorie limit: 1468kcal\n" +
                "BMI: 21.91",
                new UserProfile(
                        new Height(187.23),
                        new Weight(76.82),
                        new Calories(1468.3),
                        Gender.MALE
                ).toString());
    }

    @Test
    void parseUserProfile_h180w80l2000_success() {
        try {
            UserProfile profile = UserProfile.parseUserProfile("h/180 w/80 g/M l/2000");
            assertEquals(180.0, profile.getHeight().value);
            assertEquals(80.0, profile.getWeight().value);
            assertEquals(Gender.MALE, profile.getGender());
            assertEquals(2000.0, profile.getDailyCalorieLimit().value);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void parseUserProfile_fail() {
        assertThrows(PatternMatchFailException.class, () -> UserProfile.parseUserProfile(""));
        assertThrows(PatternMatchFailException.class, () -> UserProfile.parseUserProfile("h/ w/ g/ l/"));
        assertThrows(PatternMatchFailException.class, () -> UserProfile.parseUserProfile("h/180 w/80 g/ l/"));
        assertThrows(PatternMatchFailException.class, () -> UserProfile.parseUserProfile("h/ w/80 g/ l/2000"));
        assertThrows(PatternMatchFailException.class, () -> UserProfile.parseUserProfile("h/180 80 M 2000"));
        assertThrows(PatternMatchFailException.class,
                () -> UserProfile.parseUserProfile("180 w/80 g/M l/2000"));
        assertThrows(PatternMatchFailException.class, () -> UserProfile.parseUserProfile("180 80 M 2000"));
        assertThrows(NumberFormatException.class,
                () -> UserProfile.parseUserProfile("h/180 w/eighty g/M l/2000"));
        assertThrows(IllegalValueException.class,
                () -> UserProfile.parseUserProfile("h/0 w/80 g/M l/2000"));
        assertThrows(IllegalValueException.class,
                () -> UserProfile.parseUserProfile("h/180 w/0 g/M l/2000"));
        assertThrows(IllegalValueException.class,
                () -> UserProfile.parseUserProfile("h/180 w/80 g/M l/-2000"));
        assertThrows(IllegalValueException.class,
                () -> UserProfile.parseUserProfile("h/180 w/80 g/Q l/-2000"));
    }
}
