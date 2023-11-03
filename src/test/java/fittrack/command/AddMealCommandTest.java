package fittrack.command;

import fittrack.parser.CommandParser;
import fittrack.parser.ParseException;
import fittrack.data.Calories;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddMealCommandTest {

    @Test
    public void setArgumentsTest(){
        String args = "Meal c/100";
        AddMealCommand addMealCommand = new AddMealCommand("addmeal " + args);
        double actual = 100;
        CommandParser parser = new CommandParser();
        try {
            addMealCommand.setArguments(args, parser);
            Calories testCals = addMealCommand.getMeal().getCalories();
            assertEquals(testCals.getValue(), actual);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testHelp(){
        assertEquals(AddMealCommand.HELP, new AddMealCommand("").getHelp());
    }
}
