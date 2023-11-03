package fittrack.command;

import fittrack.WorkoutList;
import fittrack.data.Calories;
import fittrack.data.Date;
import fittrack.data.Workout;


import fittrack.parser.CommandParser;
import fittrack.parser.ParseException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CaloriesBurntCommandTest {

    private final WorkoutList workoutList = new WorkoutList();

    @BeforeEach
    public void setUp() {
        Workout workout1 = new Workout("workout1", new Calories(100), new Date("2023-10-23"));
        Workout workout2 = new Workout("workout2", new Calories(200), new Date("2023-10-23"));
        Workout workout3 = new Workout("workout3", new Calories(300), new Date("2023-10-23"));
        workoutList.addToList(workout1);
        workoutList.addToList(workout2);
        workoutList.addToList(workout3);
    }

    @Test
    public void testExecute() throws ParseException {
        CaloriesBurntCommand caloriesBurntCommand = new CaloriesBurntCommand(CaloriesBurntCommand.COMMAND_WORD);
        caloriesBurntCommand.setArguments("2023-10-23", new CommandParser());
        caloriesBurntCommand.setData(null, null, workoutList, null);
        CommandResult result = caloriesBurntCommand.execute();
        assertEquals("Total calories burnt on 2023-10-23: 600.0cals"
                , result.getFeedback());
    }

}
