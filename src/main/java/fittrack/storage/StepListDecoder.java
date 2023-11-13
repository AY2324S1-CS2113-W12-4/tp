package fittrack.storage;

import fittrack.StepList;
import fittrack.Ui;
import fittrack.data.Date;
import fittrack.data.Step;
import fittrack.parser.DateFormatException;
import fittrack.storage.Storage.StorageOperationException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// @@author J0shuaLeong
public class StepListDecoder {
    private static final Pattern STEP_PATTERN = Pattern.compile(
            "(?<steps>\\d+)\\s*\\|\\s*(?<date>\\d{4}-\\d{2}-\\d{2})"
    );
    private static final StorageOperationException CONTENT_CORRUPTION_EXCEPTION = new StorageOperationException(
            "Creating new step list file..."
    );
    private static final String FILE_NAME = "stepList.txt";


    /**
     * Decodes {@code encodedStepList} into a {@code StepList} containing the decoded data.
     *
     * @throws StorageOperationException if the {@code encodedStepList} is in an invalid format.
     */
    public static StepList decodeStepList(List<String> encodedStepList, Path stepListPath)
            throws StorageOperationException, IOException {
        StepList stepList = new StepList();
        for (String encodedSteps : encodedStepList) {
            stepList.addToList(decodeStepsFromString(encodedSteps, stepListPath));
        }
        return stepList;
    }

    /**
     * Decodes {@code encodedSteps} into a {@code Meal}.
     *
     * @throws StorageOperationException if {@code encodedPerson} is in an invalid format.
     */
    public static Step decodeStepsFromString(String encodedSteps, Path stepListPath)
            throws StorageOperationException, IOException {
        final Matcher matcher = STEP_PATTERN.matcher(encodedSteps);
        if (!matcher.matches()) {
            handleCorruptedFile(stepListPath);
            throw CONTENT_CORRUPTION_EXCEPTION;
        }

        final String stepData = matcher.group("steps");
        final String dateData = matcher.group("date");

        try {
            int step = Integer.parseInt(stepData);
            if (step <= 0) {
                handleCorruptedFile(stepListPath);
                throw CONTENT_CORRUPTION_EXCEPTION;
            }
            Date date = Date.parseDate(dateData);
            return new Step(step, date);
        } catch (NumberFormatException | DateFormatException e) {
            handleCorruptedFile(stepListPath);
            throw CONTENT_CORRUPTION_EXCEPTION;
        }
    }

    public static void handleCorruptedFile(Path filePath) throws IOException {
        String newFileContent = "";
        Ui.printPromptForCreateNewFile(FILE_NAME);
        if (Ui.createNewFile()) {
            Files.write(filePath, newFileContent.getBytes());
        } else {
            throw new Ui.ForceExitException();
        }
    }
}
