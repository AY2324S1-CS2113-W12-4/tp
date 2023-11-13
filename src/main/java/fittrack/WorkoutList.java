package fittrack;

import java.util.ArrayList;

import fittrack.data.Workout;

public class WorkoutList {
    private final ArrayList<Workout> workoutList;

    public WorkoutList() {
        workoutList = new ArrayList<>();
    }

    public ArrayList<Workout> getWorkoutList() {
        return this.workoutList;
    }

    public void addToList(Workout newWorkout) {
        workoutList.add(newWorkout);
    }

    public void deleteWorkout(int workoutIndex) {
        assert isIndexValid(workoutIndex);
        workoutList.remove((workoutIndex - 1));
    }

    // @@author farissirraj
    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();

        int index = 1;
        for (Workout workout : workoutList) {
            // Example: `1.[W] workout (100kcal, 2000-01-01)`
            String workoutWithIndex = index + "." + workout;
            output.append(workoutWithIndex).append("\n");
            index += 1;
        }
        return output.toString().strip();
    }
    // @@author

    public Workout getWorkout(int workoutIndex) {
        assert isIndexValid(workoutIndex);
        return workoutList.get(workoutIndex - 1);
    }

    public boolean isEmpty() {
        return workoutList.isEmpty();
    }

    public boolean isIndexValid(int index) {
        return 1 <= index && index <= workoutList.size();
    }
}
