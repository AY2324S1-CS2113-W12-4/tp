package fittrack.data;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class WorkoutTest {
    private static Workout tw;

    @BeforeAll
    static void beforeAll() {
        tw = new Workout("workout", new Calories(100), new Date("2023-10-30"));
    }

    @Test
    void constructor_null_assert() {
        assertThrows(
                AssertionError.class,
                () -> new Workout(null, new Calories(100), new Date("2023-10-30"))
        );
        assertThrows(
                AssertionError.class,
                () -> new Workout("workout", null, new Date("2023-10-30"))
        );
        assertThrows(
                AssertionError.class,
                () -> new Workout("workout", new Calories(100), null)
        );
    }

    @Test
    void getCalories_tw_double100() {
        assertEquals(100, tw.getCalories().value);
    }

    @Test
    void getDate_tw_date20231030() {
        assertEquals(new Date("2023-10-30"), tw.getDate());
    }

    @Test
    void getName_tw_workout() {
        assertEquals("workout", tw.getName());
    }

    @Test
    void formatToFile() {
        assertEquals("workout | 100kcal | 2023-10-30", tw.formatToFile());
    }

    @Test
    void toString_tw_success() {
        assertEquals("[W] workout (100kcal, 2023-10-30)", tw.toString());
    }
}
