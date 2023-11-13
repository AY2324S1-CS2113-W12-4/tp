package fittrack.data;

import fittrack.parser.IllegalValueException;
import fittrack.parser.NumberFormatException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SuppressWarnings("AssertBetweenInconvertibleTypes")
class CaloriesTest {

    @Test
    void constructor_zero_success() {
        assertDoesNotThrow(() -> new Calories(0));
        assertDoesNotThrow(() -> new Calories(1));
        assertDoesNotThrow(() -> new Calories(Calories.MAX_VALUE));
    }

    @Test
    void constructor_belowZero_assert() {
        assertThrows(AssertionError.class, () -> new Calories(-1));
        assertThrows(AssertionError.class, () -> new Calories(Calories.MAX_VALUE + 1));
    }

    @Test
    void equals_same_true() {
        assertEquals(new Calories(433.13), new Calories(433.13));
    }

    @Test
    void equals_different_false() {
        assertNotEquals(null, new Calories(433.13));
        assertNotEquals(new Height(433.13), new Calories(433.13));
        assertNotEquals(new Calories(433.93), new Calories(433.13));
    }

    @Test
    void toString_cal433o41_str433kcal() {
        assertEquals("433kcal", new Calories(433.41).toString());
    }

    @Test
    void parseCalories_c433o13_success() {
        try {
            assertEquals(new Calories(433.13), Calories.parseCalories("433.13"));
        } catch (IllegalValueException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void parseCalories_fail() {
        assertThrows(AssertionError.class, () -> Calories.parseCalories(null));
        assertThrows(NumberFormatException.class, () -> Calories.parseCalories(""));
        assertThrows(NumberFormatException.class, () -> Calories.parseCalories(" "));
        assertThrows(NumberFormatException.class, () -> Calories.parseCalories("hi"));
        assertThrows(NumberFormatException.class, () -> Calories.parseCalories("344kcal"));
        assertThrows(IllegalValueException.class, () -> Calories.parseCalories("-0.01"));
        assertThrows(IllegalValueException.class, () -> Calories.parseCalories("9999999999"));
    }
}
