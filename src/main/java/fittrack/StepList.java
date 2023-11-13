package fittrack;

import fittrack.data.Step;

import java.util.ArrayList;

public class StepList {

    private int stepListSize = 0;
    private final ArrayList<Step> stepList;

    public StepList() {
        stepList = new ArrayList<>();
    }

    public ArrayList<Step> getStepList() {
        return this.stepList;
    }

    public void addToList(Step newStep) {
        stepList.add(newStep);
        stepListSize++;
    }

    public void deleteStep(int stepIndex) {
        assert isIndexValid(stepIndex);
        stepList.remove((stepIndex - 1));
        stepListSize--;
    }

    @Override
    public String toString() {
        int counter = 1;
        StringBuilder output = new StringBuilder();
        for (Step step : stepList) {
            output.append(counter).append(".").append(step.toString()).append("\n");
            counter += 1;
        }
        return output.toString().strip();
    }

    public Step getStep(int stepIndex) {
        assert isIndexValid(stepIndex);
        return stepList.get(stepIndex - 1);
    }

    public boolean isIndexValid(int index) {
        return 1 <= index && index <= stepList.size();
    }

    public boolean isEmpty() {
        return stepListSize == 0;
    }
}
