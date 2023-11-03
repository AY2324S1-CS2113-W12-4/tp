package fittrack.data;

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
        assertEquals(new Date("2023-10-30"), tm.getMealDate());
    }

    @Test
    void getName_tm_meal() {
        assertEquals("meal", tm.getName());
    }

    @Test
    void toString_tm_success() {
        assertEquals("[M] meal (100kcal, 2023-10-30)", tm.toString());
    }
}
