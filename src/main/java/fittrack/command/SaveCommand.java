package fittrack.command;

import fittrack.parser.CommandParser;
import fittrack.storage.Storage;

import java.io.IOException;

public class SaveCommand extends Command {
    public static final String COMMAND_WORD = "save";
    private static final String DESCRIPTION =
            String.format("`%s` saves your profile, meals and workout data.", COMMAND_WORD);
    private static final String USAGE =
            String.format("Type `%s` to save your profile, meals and workout data.", COMMAND_WORD);
    public static final String HELP = DESCRIPTION + "\n" + USAGE;
    Storage storage;

    @Override
    public CommandResult execute() {
        // TODO: get profile details and make them to lines of strings.
        try {
            storage.saveProfile();
        } catch (IOException e) {
            System.out.println(e);
        }
        return new CommandResult("Your Profile settings and details has been saved!");
    }

    @Override
    public void setArguments(String args, CommandParser parser) {
    }

    @Override
    protected String getHelp() {
        return HELP;
    }
}
