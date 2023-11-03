package fittrack.command;

import static org.junit.jupiter.api.Assertions.assertEquals;

import fittrack.WorkoutList;
import fittrack.data.Calories;
import fittrack.data.Date;
import fittrack.data.Workout;
import fittrack.parser.CommandParser;
import fittrack.parser.PatternMatchFailException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FindWorkoutCommandTest {

    private static final String COMMAND = "findworkout";
    private WorkoutList workoutList = new WorkoutList();
    private Workout workout1 = new Workout("short run", new Calories(120), new Date("2023-10-31"));
    private Workout workout2 = new Workout("walk", new Calories(100), new Date("2023-10-29"));
    private Workout workout3 = new Workout("long run", new Calories(80), new Date("2023-10-28"));
    private final String result1 = "There are 2 workouts that contains run";
    private final String result2 = "Sorry, there are no such workouts found.";


    @BeforeEach
    void setup() {
        workoutList.addToList(workout1);
        workoutList.addToList(workout2);
        workoutList.addToList(workout3);
    }

    @Test
    public void execute() throws PatternMatchFailException, NullPointerException {
        assertFindCommandBehavior(COMMAND, "run", result1);
        assertFindCommandBehavior(COMMAND, "swim", result2);
    }

    /**
     * Executes the find command for the given keywords and verifies
     * the result matches the workout in the expectedWorkoutList exactly.
     */
    void assertFindCommandBehavior(String commandLine, String keyword, String expectedResult)
            throws PatternMatchFailException {
        FindWorkoutCommand findCommand = new FindWorkoutCommand(commandLine);
        findCommand.setArguments(keyword, new CommandParser());
        findCommand.setData(null, null, workoutList, null);
        CommandResult result = findCommand.execute();
        assertEquals(expectedResult, result.getFeedback());
    }

    @Test
    public void testHelp(){
        assertEquals(FindWorkoutCommand.HELP, new FindWorkoutCommand("").getHelp());
    }
}
