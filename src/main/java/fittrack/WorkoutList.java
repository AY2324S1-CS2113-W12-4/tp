package fittrack;

import fittrack.data.Workout;

import java.util.ArrayList;

public class WorkoutList {
    private int workoutListSize = 0;
    private ArrayList<Workout> workoutList;

    public WorkoutList() {
        workoutList = new ArrayList<>();
    }

    //TODO load contents into workoutlist
    public WorkoutList(ArrayList<Workout> workoutList) {
        this.workoutList = workoutList;
    }

    public ArrayList<Workout> getWorkoutList() {
        return this.workoutList;
    }

    public void addToList(Workout newWorkout) {
        workoutList.add(newWorkout);
        workoutListSize++;
    }

    public void deleteWorkout(int workoutIndex) {
        workoutList.remove((workoutIndex - 1));
        workoutListSize--;
    }

    public int getWorkoutListSize() {
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
