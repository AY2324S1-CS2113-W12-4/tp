package fittrack;

import java.util.ArrayList;

public class WorkoutList {

    private static ArrayList<Workout> workoutList;
    private static int workoutListSize = 0;

    public WorkoutList() {
        workoutList = new ArrayList<>();
    }

    public static void addToList(Workout newWorkout) {
        workoutList.add(newWorkout);
        workoutListSize++;
    }

    public static void deleteWorkout(int workoutIndex) {
        workoutList.remove((workoutIndex - 1));
        workoutListSize--;
    }

    public static int getWorkoutListSize() {
        return workoutListSize;
    }

    @Override
    public String toString() {
        int counter = 1;
        StringBuilder output = new StringBuilder();
        for (Workout workout : workoutList) {
            output.append(counter).append(".").append(workout.toString()).append("\n");
            counter += 1;
        }
        return output.toString();
    }

    public Workout getWorkout(int workoutIndex) {
        return workoutList.get(workoutIndex - 1);
    }

}
