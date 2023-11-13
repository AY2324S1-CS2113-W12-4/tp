package fittrack.data;

import fittrack.parser.DateFormatException;
import fittrack.parser.NumberFormatException;
import fittrack.parser.ParseException;
import fittrack.parser.PatternMatchFailException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MealTest {
    private static Meal tm;

    @BeforeAll
    static void beforeAll() {
        tm = new Meal("meal", new Calories(100), new Date("2023-10-30"));
    }

    @Test
    void constructor_null_assert() {
        assertThrows(
                AssertionError.class,
                () -> new Meal(null, new Calories(100), new Date("2023-10-30"))
        );
        assertThrows(
                AssertionError.class,
                () -> new Meal("meal", null, new Date("2023-10-30"))
        );
        assertThrows(
                AssertionError.class,
                () -> new Meal("meal", new Calories(100), null)
        );
    }

    @Test
    void formatToFile_tm_success() {
        assertEquals("meal | 100kcal | 2023-10-30", tm.formatToFile());
    }

    @Test
    void getCalories_tm_cal100() {
        assertEquals(new Calories(100), tm.getCalories());
    }

    @Test
    void getMealDate_tm_date20231030() {
        assertEquals(new Date("2023-10-30"), tm.getDate());
    }

    @Test
    void getName_tm_meal() {
        assertEquals("meal", tm.getName());
    }

    @Test
    void toString_tm_success() {
        assertEquals("[M] meal (100kcal, 2023-10-30)", tm.toString());
    }

    @Test
    void parseMeal_nc12345_success() {
        try {
            Meal meal = Meal.parseMeal("name c/123.45");
            assertEquals("name", meal.getName());
            assertEquals(123.45, meal.getCalories().value);
            assertEquals(Date.today(), meal.getDate());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void parseMeal_nc12345d20231031_success() {
        try {
            Meal meal = Meal.parseMeal("name c/123.45 d/2023-10-31");
            assertEquals("name", meal.getName());
            assertEquals(123.45, meal.getCalories().value);
            assertEquals(new Date(2023, 10, 31), meal.getDate());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void parseMeal_fail() {
        assertThrows(AssertionError.class, () -> Meal.parseMeal(null));
        assertThrows(PatternMatchFailException.class, () -> Meal.parseMeal(""));
        assertThrows(PatternMatchFailException.class, () -> Meal.parseMeal(" "));
        assertThrows(PatternMatchFailException.class, () -> Meal.parseMeal("namec/123d/2023-10-31"));
        assertThrows(PatternMatchFailException.class, () -> Meal.parseMeal("name"));
        assertThrows(PatternMatchFailException.class, () -> Meal.parseMeal("name c/"));
        assertThrows(PatternMatchFailException.class, () -> Meal.parseMeal("name c/123 d/"));
        assertThrows(PatternMatchFailException.class, () -> Meal.parseMeal("c/123 d/2023-10-31"));
        assertThrows(DateFormatException.class, () -> Meal.parseMeal("name c/100 d/oct31"));
        assertThrows(NumberFormatException.class, () -> Meal.parseMeal("name c/hundred"));
    }
}
