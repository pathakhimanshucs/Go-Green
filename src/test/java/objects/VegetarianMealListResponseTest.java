package objects;

import org.junit.Test;

import java.util.LinkedList;

import static org.junit.Assert.*;

public class VegetarianMealListResponseTest {

    @Test
    public void getEmail() {
        VegetarianMealListResponse response = new VegetarianMealListResponse();
        response.setEmail("kek");
        assertEquals(response.getEmail(), "kek");
    }

    @Test
    public void getMeals() {
        VegetarianMealListResponse response = new VegetarianMealListResponse();
        LinkedList list = new LinkedList<Meal>();
        Meal meal = new Meal();
        list.add(meal);
        response.setMeals(list);
        assertEquals(response.getMeals(), list);
    }

    @Test
    public void addMeal() {
        VegetarianMealListResponse response = new VegetarianMealListResponse();
        LinkedList list = new LinkedList<Meal>();
        Meal meal = new Meal();
        meal.setMealAmount(69);
        list.add(meal);
        Meal meal2 = new Meal();
        meal2.setMealAmount(69);
        response.addMeal(meal2);
        assertEquals(response.getMeals(), list);
    }

    @Test
    public void addOtherMeal() {
        VegetarianMealListResponse response = new VegetarianMealListResponse();
        LinkedList list = new LinkedList<Meal>();
        Meal meal = new Meal();
        meal.setMealAmount(69);
        Meal meal2 = new Meal();
        meal2.setMealAmount(69);
        list.add(meal2);
        list.add(meal);
        response.addMeal(meal2);
        response.addMeal(meal);
        assertEquals(response.getMeals(), list);
    }

    @Test
    public void isMealsListSuccess() {
        VegetarianMealListResponse response = new VegetarianMealListResponse();
        response.setMealsListSuccess(true);
        assertEquals(response.isMealsListSuccess(), true);
    }

    @Test
    public void equalsSelf() {
        VegetarianMealListResponse response = new VegetarianMealListResponse();
        response.setMealsListSuccess(true);
        assertTrue(response.equals(response));
    }

    @Test
    public void equalsNull() {
        VegetarianMealListResponse response = new VegetarianMealListResponse();
        response.setMealsListSuccess(true);
        assertFalse(response.equals(null));
    }

    @Test
    public void equalsElse() {
        VegetarianMealListResponse response = new VegetarianMealListResponse();
        response.setMealsListSuccess(true);
        Meal meal = new Meal();
        assertFalse(response.equals(meal));
    }

    @Test
    public void equalsTrue() {
        VegetarianMealListResponse response = new VegetarianMealListResponse();
        VegetarianMealListResponse response2 = new VegetarianMealListResponse();
        response.setMealsListSuccess(true);
        response2.setMealsListSuccess(true);
        assertTrue(response.equals(response2));
    }

    @Test
    public void equalsFalse() {
        VegetarianMealListResponse response = new VegetarianMealListResponse();
        VegetarianMealListResponse response2 = new VegetarianMealListResponse();
        response.setMealsListSuccess(true);
        response2.setMealsListSuccess(false);
        assertFalse(response.equals(response2));
    }

    @Test
    public void equalsEmailFalse() {
        VegetarianMealListResponse response = new VegetarianMealListResponse();
        VegetarianMealListResponse response2 = new VegetarianMealListResponse();
        response.setMealsListSuccess(true);
        response2.setMealsListSuccess(true);
        response.setEmail("top");
        response2.setEmail("kek");
        assertFalse(response.equals(response2));
    }

    @Test
    public void equalsEmailFalse2() {
        VegetarianMealListResponse response = new VegetarianMealListResponse();
        VegetarianMealListResponse response2 = new VegetarianMealListResponse();
        response.setMealsListSuccess(true);
        response2.setMealsListSuccess(true);
        response.setEmail("kek");
        assertFalse(response.equals(response2));
    }

    @Test
    public void equalsEmailFalse3() {
        VegetarianMealListResponse response = new VegetarianMealListResponse();
        VegetarianMealListResponse response2 = new VegetarianMealListResponse();
        response.setMealsListSuccess(true);
        response2.setMealsListSuccess(true);
        response2.setEmail("kek");
        assertFalse(response.equals(response2));
    }

    @Test
    public void equalsEmailTrue() {
        VegetarianMealListResponse response = new VegetarianMealListResponse();
        VegetarianMealListResponse response2 = new VegetarianMealListResponse();
        response.setMealsListSuccess(true);
        response2.setMealsListSuccess(true);
        response.setEmail("kek");
        response2.setEmail("kek");
        assertTrue(response.equals(response2));
    }

    @Test
    public void equalsFalse2() {
        VegetarianMealListResponse response = new VegetarianMealListResponse();
        VegetarianMealListResponse response2 = new VegetarianMealListResponse();
        response2.setMealsListSuccess(true);
        response.setEmail("kek");
        response2.setEmail("kek");
        assertFalse(response.equals(response2));
    }

    @Test
    public void equalsFalse3() {
        VegetarianMealListResponse response = new VegetarianMealListResponse();
        VegetarianMealListResponse response2 = new VegetarianMealListResponse();
        response.setMealsListSuccess(true);
        response.setEmail("kek");
        response2.setEmail("kek");
        assertFalse(response.equals(response2));
    }

    @Test
    public void equalsTrue2() {
        VegetarianMealListResponse response = new VegetarianMealListResponse();
        VegetarianMealListResponse response2 = new VegetarianMealListResponse();
        response.setEmail("kek");
        response2.setEmail("kek");
        assertTrue(response.equals(response2));
    }

    @Test
    public void equalsMealsFalse() {
        VegetarianMealListResponse response = new VegetarianMealListResponse();
        VegetarianMealListResponse response2 = new VegetarianMealListResponse();
        Meal meal = new Meal();
        response.addMeal(meal);
        response2.addMeal(meal);
        response2.addMeal(meal);
        assertFalse(response.equals(response2));
    }

    @Test
    public void equalsMealsFalse2() {
        VegetarianMealListResponse response = new VegetarianMealListResponse();
        VegetarianMealListResponse response2 = new VegetarianMealListResponse();
        Meal meal = new Meal();
        response.addMeal(meal);
        assertFalse(response.equals(response2));
    }

    @Test
    public void equalsMealsFalse3() {
        VegetarianMealListResponse response = new VegetarianMealListResponse();
        VegetarianMealListResponse response2 = new VegetarianMealListResponse();
        Meal meal = new Meal();
        response2.addMeal(meal);
        assertFalse(response.equals(response2));
    }

    @Test
    public void equalsMealsTrue() {
        VegetarianMealListResponse response = new VegetarianMealListResponse();
        VegetarianMealListResponse response2 = new VegetarianMealListResponse();
        Meal meal = new Meal();
        response.addMeal(meal);
        response2.addMeal(meal);
        assertTrue(response.equals(response2));
    }

}