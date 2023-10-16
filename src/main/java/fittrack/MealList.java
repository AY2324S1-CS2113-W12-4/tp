package fittrack;

import java.util.ArrayList;

public class MealList {

    private static ArrayList<Meal> mealList;

    public MealList() {
        mealList = new ArrayList<>();
    }

    public static void addToList(Meal newMeal) {
        mealList.add(newMeal);
    }

    public static void deleteMeal(int mealIndex) {
        mealList.remove((mealIndex - 1));
    }
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
        return mealList.get(mealIndex - 1);

    }
}
