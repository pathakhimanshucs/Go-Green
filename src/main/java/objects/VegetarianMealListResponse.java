package objects;

import java.util.LinkedList;

public class VegetarianMealListResponse {
    private String email;
    private LinkedList<Meal> meals;
    private boolean mealsListSuccess;

    public VegetarianMealListResponse() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LinkedList<Meal> getMeals() {
        return meals;
    }

    public void setMeals(LinkedList<Meal> meals) {
        this.meals = meals;
    }

    public void addMeal(Meal meal) {
        meals.add(meal);
    }

    public boolean isMealsListSuccess() {
        return mealsListSuccess;
    }

    public void setMealsListSuccess(boolean mealsListSuccess) {
        this.mealsListSuccess = mealsListSuccess;
    }
}
