package fittrack;

import fittrack.data.Meal;

import java.util.ArrayList;

public class MealList {

    private int mealListSize = 0;
    private ArrayList<Meal> mealList;

    public MealList() {
        mealList = new ArrayList<>();
    }

    public ArrayList<Meal> getMealList() {
        return this.mealList;
    }

    public void addToList(Meal newMeal) {
        mealList.add(newMeal);
        mealListSize++;
    }

    // @@author NgLixuanNixon
    public void deleteMeal(int mealIndex) {
        assert isIndexValid(mealIndex);
        mealList.remove((mealIndex - 1));
        mealListSize--;
    }
    // @@author

    @Override
    public String toString() {
        int counter = 1;
        StringBuilder output = new StringBuilder();
        for (Meal meal : mealList) {
            output.append(counter).append(".").append(meal.toString()).append("\n");
            counter += 1;
        }
        return output.toString();
    }

    public Meal getMeal(int mealIndex) {
        assert isIndexValid(mealIndex);
        return mealList.get(mealIndex - 1);
    }

    public boolean isIndexValid(int index) {
        return 1 <= index && index <= mealList.size();
    }
}
