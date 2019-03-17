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

    public void addMeal (Meal meal){
        meals.add(meal);
    }

    public boolean isMealsListSuccess() {
        return mealsListSuccess;
    }

    public void setMealsListSuccess(boolean mealsListSuccess) {
        this.mealsListSuccess = mealsListSuccess;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        VegetarianMealListResponse that = (VegetarianMealListResponse) o;

        if (mealsListSuccess != that.mealsListSuccess) {
            return false;
        }
        if (email != null ? !email.equals(that.email) : that.email != null) {
            return false;
        }
        return meals != null ? meals.equals(that.meals) : that.meals == null;
    }
}
