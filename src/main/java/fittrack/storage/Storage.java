package fittrack.storage;

import fittrack.data.Height;
import fittrack.data.Weight;
import fittrack.data.Calories;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

//TODO pass the profile data to be stored in fittrack.txt
public class Storage {

    Height height;
    Weight weight;
    Calories calories;
    private final String profileFilePath = "./data/fittrack.txt";
    private final File file;

    /**
     * Constructs storage. Creates new file fittrack.txt
     * in a directory called data if none exist.
     */
    public Storage() {
        this.file = new File(profileFilePath);
        assert file != null;
        try {
            if (!this.file.exists()) { // If file does not exist, folder does not exist
                file.getParentFile().mkdir(); // Creates data folder
                file.createNewFile(); // throws IOException, create a file in abstract dir
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    /**
     * Saves user profile data into storage
     *
     * @throws IOException error
     */
    public void saveProfile() throws IOException {
        //TODO write data to file
        FileWriter file = new FileWriter(profileFilePath);
        String heightSaveString = height.toString();
        file.write(heightSaveString + "\n");
        String weightSaveString = weight.toString();
        file.write(weightSaveString + "\n");
        String caloriesSaveString = calories.toString();
        file.write(caloriesSaveString + "\n");
        file.close();
    }
}
