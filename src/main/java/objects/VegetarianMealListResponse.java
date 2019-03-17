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

    /**
     * Adds meal to list or creates a new list and adds the meak.
     * @param meal Meal to be added.
     */
    public void addMeal(Meal meal) {
        if (meals != null) {
            meals.add(meal);
        } else {
            meals = new LinkedList<>();
            meals.add(meal);
        }
    }

    public boolean isMealsListSuccess() {
        return mealsListSuccess;
    }

    public void setMealsListSuccess(boolean mealsListSuccess) {
        this.mealsListSuccess = mealsListSuccess;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        VegetarianMealListResponse that = (VegetarianMealListResponse) obj;

        if (mealsListSuccess != that.mealsListSuccess) {
            return false;
        }
        if (email != null ? !email.equals(that.email) : that.email != null) {
            return false;
        }
        return meals != null ? meals.equals(that.meals) : that.meals == null;
    }
}
