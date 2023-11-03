package fittrack.command;

import fittrack.MealList;
import fittrack.data.Calories;
import fittrack.data.Date;
import fittrack.data.Meal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalorieSumCommandTest {
    private final MealList mealList = new MealList();

    @BeforeEach
    public void setUp() {
        Meal meal1 = new Meal("meal1", new Calories(100), new Date("2023-10-23"));
        Meal meal2 = new Meal("meal2", new Calories(200), new Date("2023-10-23"));
        Meal meal3 = new Meal("meal3", new Calories(300), new Date("2023-10-23"));
        mealList.addToList(meal1);
        mealList.addToList(meal2);
        mealList.addToList(meal3);
    }

    @Test
    public void testExecute(){
        CalorieSumCommand calorieSumCommand = new CalorieSumCommand(CalorieSumCommand.COMMAND_WORD);
        calorieSumCommand.mealList = mealList;
        calorieSumCommand.execute();
        assertEquals(calorieSumCommand.getCalorieSum(), 600);

    }

}
