package fittrack.command;

import fittrack.parser.ParseException;
import fittrack.data.Calories;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

// @@author NgLixuanNixon
public class AddMealCommandTest {

    @Test
    public void setArgumentsTest(){
        String args = "Meal c/100";
        AddMealCommand addMealCommand = new AddMealCommand("addmeal " + args);
        double actual = 100;
        try {
            addMealCommand.setArguments(args);
            Calories testCals = addMealCommand.getMeal().getCalories();
            assertEquals(testCals.value, actual);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testHelp(){
        assertEquals(AddMealCommand.HELP, new AddMealCommand("").getHelp());
    }
}
// @@author
