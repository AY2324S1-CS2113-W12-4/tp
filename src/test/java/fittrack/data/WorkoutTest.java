package fittrack.data;

import fittrack.parser.DateFormatException;
import fittrack.parser.NumberFormatException;
import fittrack.parser.ParseException;
import fittrack.parser.PatternMatchFailException;
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


    @Test
    void parseWorkout_nc12345_success() {
        try {
            Workout workout = Workout.parseWorkout("name c/123.45");
            assertEquals("name", workout.getName());
            assertEquals(123.45, workout.getCalories().value);
            assertEquals(Date.today(), workout.getDate());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void parseWorkout_nc12345d20231031_success() {
        try {
            Workout workout = Workout.parseWorkout("name c/123.45 d/2023-10-31");
            assertEquals("name", workout.getName());
            assertEquals(123.45, workout.getCalories().value);
            assertEquals(new Date(2023, 10, 31), workout.getDate());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void parseWorkout_fail() {
        assertThrows(AssertionError.class, () -> Workout.parseWorkout(null));
        assertThrows(PatternMatchFailException.class, () -> Workout.parseWorkout(""));
        assertThrows(PatternMatchFailException.class, () -> Workout.parseWorkout(" "));
        assertThrows(PatternMatchFailException.class, () -> Workout.parseWorkout("namec/123d/2023-10-31"));
        assertThrows(PatternMatchFailException.class, () -> Workout.parseWorkout("name"));
        assertThrows(PatternMatchFailException.class, () -> Workout.parseWorkout("name c/"));
        assertThrows(PatternMatchFailException.class, () -> Workout.parseWorkout("name c/123 d/"));
        assertThrows(PatternMatchFailException.class, () -> Workout.parseWorkout("c/123 d/2023-10-31"));
        assertThrows(DateFormatException.class, () -> Workout.parseWorkout("name c/100 d/oct31"));
        assertThrows(NumberFormatException.class, () -> Workout.parseWorkout("name c/hundred"));
    }
}
