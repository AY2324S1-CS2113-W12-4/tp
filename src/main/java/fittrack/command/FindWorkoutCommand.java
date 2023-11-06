package fittrack.command;

import fittrack.data.Workout;
import fittrack.parser.CommandParser;
import fittrack.parser.PatternMatchFailException;

import java.util.ArrayList;

public class FindWorkoutCommand extends Command {

    public static final String COMMAND_WORD = "findworkout";
    private static final String DESCRIPTION =
            String.format("`%s` finds the workout you are looking for in your workout list.", COMMAND_WORD);
    private static final String USAGE = String.format("Type `%s <KEYWORD>` to find a workout.", COMMAND_WORD);

    public static final String HELP = DESCRIPTION + "\n" + USAGE;

    private String keyword;

    public FindWorkoutCommand(String commandLine) {
        super(commandLine);
    }

    @Override
    public CommandResult execute() {
        StringBuilder feedbackBuilder = new StringBuilder();

        ArrayList<Workout> workouts = workoutList.getWorkoutList();
        int workoutNum = 0;
        int numFound = 0;
        boolean workoutFound = false;
        for (Workout workout : workouts) {
            if (workout.getName().contains(keyword)) {
                if (!workoutFound) {
                    workoutFound = true;
                    String foundMessage = "These workouts contain the keyword " + keyword + ":";
                    feedbackBuilder.append(foundMessage).append("\n");
                }
                String workoutWithNumber = (workoutNum + 1) + "." + workout;
                feedbackBuilder.append(workoutWithNumber).append("\n");
                numFound++;
            }
            workoutNum++;
        }
        if (!workoutFound) {
            return new CommandResult("Sorry, there are no such workouts found.");
        }

        String summary = "There are " + numFound + " workouts that contains " + keyword + ".";
        feedbackBuilder.append(summary);
        return new CommandResult(feedbackBuilder.toString());
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
