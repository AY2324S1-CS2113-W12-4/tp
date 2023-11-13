package fittrack.command;

import fittrack.parser.ParseException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

// @@author farissirraj
public class AddWorkoutCommandTest {

    @Test
    public void setArgumentsTest(){
        String args = "Workout c/100";
        AddWorkoutCommand addWorkoutCommand = new AddWorkoutCommand("addworkout " + args);
        double actual = 100;
        try {
            addWorkoutCommand.setArguments(args);
            double testCals = addWorkoutCommand.getWorkout().getCalories().value;
            assertEquals(testCals, actual);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testHelp(){
        assertEquals(AddWorkoutCommand.HELP, new AddWorkoutCommand("").getHelp());
    }
}
// @@author
