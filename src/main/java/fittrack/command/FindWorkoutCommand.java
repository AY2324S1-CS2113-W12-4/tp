package fittrack.command;

import fittrack.Ui;
import fittrack.data.Workout;
import fittrack.parser.CommandParser;
import fittrack.parser.PatternMatchFailException;

import java.util.ArrayList;

public class FindWorkoutCommand extends Command {

    public static final String COMMAND_WORD = "findworkout";
    private static final String DESCRIPTION =
            String.format("`%s` finds the workout you are looking for in your workout list.", COMMAND_WORD);
    private static final String USAGE = String.format("Type `%s <keyword>` to find a workout.\n", COMMAND_WORD);

    public static final String HELP = DESCRIPTION + "\n" + USAGE;
    private Ui ui = new Ui();
    private String keyword;

    public FindWorkoutCommand(String commandLine) {
        super(commandLine);
    }

    @Override
    public CommandResult execute() {
        ArrayList<Workout> workouts = workoutList.getWorkoutList();
        int workoutNum = 0;
        int numFound = 0;
        boolean workoutFound = false;
        for (Workout workout : workouts) {
            if (workout.getName().contains(keyword)) {
                if (!workoutFound) {
                    workoutFound = true;
                    ui.printFoundMessage("workouts", keyword);
                }
                ui.printWorkoutWithNumber(workoutNum, workout);
                numFound++;
            }
            workoutNum++;
        }
        if (!workoutFound) {
            return new CommandResult("Sorry, there are no such workouts found.");
        }
        return new CommandResult("There are " + numFound + " workouts that contains " + keyword);
    }

    @Override
    public void setArguments(String args, CommandParser parser)
            throws PatternMatchFailException {
        if (args.isEmpty()) {
            throw new PatternMatchFailException();
        }
        keyword = parser.parseFind(args);
    }

    @Override
    protected String getHelp() {
        return HELP;
    }
}
