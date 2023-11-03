package fittrack.command;

import static org.junit.jupiter.api.Assertions.assertEquals;

import fittrack.MealList;
import fittrack.data.Calories;
import fittrack.data.Date;
import fittrack.data.Meal;
import fittrack.parser.CommandParser;
import fittrack.parser.PatternMatchFailException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FindMealCommandTest {

    private MealList mealList = new MealList();
    private Meal meal1 = new Meal("pasta", new Calories(120), new Date("2023-10-31"));
    private Meal meal2 = new Meal("cabonara pasta", new Calories(100), new Date("2023-10-29"));
    private Meal meal3 = new Meal("chicken", new Calories(80), new Date("2023-10-28"));
    private final String result1 = "There are 2 meals that contains pasta";


    @BeforeEach
    void setup() {
        mealList.addToList(meal1);
        mealList.addToList(meal2);
        mealList.addToList(meal3);
    }

    @Test
    public void execute() throws PatternMatchFailException, NullPointerException {
        assertFindCommandBehavior("findmeal", "pasta");
    }

    /**
     * Executes the find command for the given keywords and verifies
     * the result matches the meals in the expectedMealList exactly.
     */
    void assertFindCommandBehavior(String commandLine, String keyword) throws PatternMatchFailException {
        FindMealCommand findCommand = new FindMealCommand(commandLine);
        findCommand.setArguments(keyword, new CommandParser());
        findCommand.setData(null, mealList, null, null);
        CommandResult result = findCommand.execute();
        assertEquals(result1, result.getFeedback());
    }

    @Test
    public void testHelp(){
        assertEquals(FindMealCommand.HELP, new FindMealCommand("").getHelp());
    }
}
