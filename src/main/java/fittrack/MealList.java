package fittrack;

import fittrack.data.Meal;

import java.util.ArrayList;

public class MealList {
    private final ArrayList<Meal> mealList;

    public MealList() {
        mealList = new ArrayList<>();
    }

    public ArrayList<Meal> getMealList() {
        return this.mealList;
    }

    public void addToList(Meal newMeal) {
        mealList.add(newMeal);
    }

    // @@author NgLixuanNixon
    public void deleteMeal(int mealIndex) {
        assert isIndexValid(mealIndex);
        mealList.remove((mealIndex - 1));
    }
    // @@author

    // @@author NgLixuanNixon
    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();

        int index = 1;
        for (Meal meal : mealList) {
            // Example: `1.[M] meal (100kcal, 2000-01-01)`
            String mealWithIndex = index + "." + meal;
            output.append(mealWithIndex).append("\n");
            index += 1;
        }
        return output.toString().strip();
    }
    // @@author

    public Meal getMeal(int mealIndex) {
        assert isIndexValid(mealIndex);
        return mealList.get(mealIndex - 1);
    }

    public boolean isEmpty() {
        return mealList.isEmpty();
    }

    public boolean isIndexValid(int index) {
        return 1 <= index && index <= mealList.size();
    }
}
