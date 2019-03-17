package objects;

import org.junit.Test;

import java.sql.Timestamp;

import static org.junit.Assert.*;

public class MealTest {

    @Test
    public void getTime() {
        Meal meal = new Meal();
        Timestamp time = new Timestamp(420);
        meal.setTime(time);
        assertEquals(meal.getTime(), time);
    }

    @Test
    public void getMealAmount() {
        Meal meal = new Meal();
        meal.setMealAmount(69);
        assertEquals(meal.getMealAmount(), 69);
    }

    @Test
    public void equalsSelf() {
        Meal meal = new Meal();
        assertTrue(meal.equals(meal));
    }

    @Test
    public void equalsNull() {
        Meal meal = new Meal();
        assertFalse(meal.equals(null));
    }

    @Test
    public void equalsElse() {
        Meal meal = new Meal();
        LoginRequest loginReq = new LoginRequest();
        assertFalse(meal.equals(loginReq));
    }

    @Test
    public void equalsTrue() {
        Meal meal = new Meal();
        Meal meal2 = new Meal();
        meal.setMealAmount(69);
        meal2.setMealAmount(69);
        Timestamp time = new Timestamp(420);
        meal.setTime(time);
        meal2.setTime(time);
        assertTrue(meal.equals(meal2));
    }

    @Test
    public void equalsFalse() {
        Meal meal = new Meal();
        Meal meal2 = new Meal();
        meal.setMealAmount(69);
        meal2.setMealAmount(1337);
        Timestamp time = new Timestamp(420);
        meal.setTime(time);
        meal2.setTime(time);
        assertFalse(meal.equals(meal2));
    }

    @Test
    public void equalsFalse2() {
        Meal meal = new Meal();
        Meal meal2 = new Meal();
        meal.setMealAmount(69);
        meal2.setMealAmount(69);
        Timestamp time = new Timestamp(420);
        Timestamp time2 = new Timestamp(1337);
        meal.setTime(time);
        meal2.setTime(time2);
        assertFalse(meal.equals(meal2));
    }
}