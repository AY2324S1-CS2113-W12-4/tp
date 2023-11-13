package fittrack.command;

import fittrack.MealList;
import fittrack.data.Calories;
import fittrack.data.Date;
import fittrack.data.Meal;

import fittrack.parser.DateFormatException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CaloriesConsumedCommandTest {
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
        CaloriesConsumedCommand caloriesConsumedCommand =
                new CaloriesConsumedCommand(CaloriesConsumedCommand.COMMAND_WORD);
        caloriesConsumedCommand.mealList = mealList;
        try {
            caloriesConsumedCommand.setArguments("2023-10-23");
        } catch (DateFormatException e) {
            throw new RuntimeException(e);
        }
        caloriesConsumedCommand.execute();
        assertEquals(caloriesConsumedCommand.getCaloriesConsumed().value, 600);

    }

}
