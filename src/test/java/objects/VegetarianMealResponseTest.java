package objects;

import org.junit.Test;

import static org.junit.Assert.*;

public class VegetarianMealResponseTest {

    @Test
    public void isAddVegetarianMealSuccess() {
        VegetarianMealResponse vegMeal = new VegetarianMealResponse();
        vegMeal.setAddVegetarianMealSuccess(true);
        assertEquals(vegMeal.isAddVegetarianMealSuccess(), true);
    }

    @Test
    public void equalsSelf(){
        VegetarianMealResponse vegMeal = new VegetarianMealResponse();
        vegMeal.setAddVegetarianMealSuccess(true);
        assertTrue(vegMeal.equals(vegMeal));
    }

    @Test
    public void equalsNull(){
        VegetarianMealResponse vegMeal = new VegetarianMealResponse();
        vegMeal.setAddVegetarianMealSuccess(true);
        assertFalse(vegMeal.equals(null));
    }

    @Test
    public void equalsElse(){
        VegetarianMealResponse vegMeal = new VegetarianMealResponse();
        vegMeal.setAddVegetarianMealSuccess(true);
        LoginResponse loginRes = new LoginResponse();
        assertFalse(vegMeal.equals(loginRes));
    }

    @Test
    public void equalsTrue(){
        VegetarianMealResponse vegMeal = new VegetarianMealResponse();
        vegMeal.setAddVegetarianMealSuccess(true);
        VegetarianMealResponse vegMeal2 = new VegetarianMealResponse();
        vegMeal2.setAddVegetarianMealSuccess(true);
        assertTrue(vegMeal.equals(vegMeal2));
    }

    @Test
    public void equalsFalse(){
        VegetarianMealResponse vegMeal = new VegetarianMealResponse();
        vegMeal.setAddVegetarianMealSuccess(true);
        VegetarianMealResponse vegMeal2 = new VegetarianMealResponse();
        vegMeal2.setAddVegetarianMealSuccess(false);
        assertFalse(vegMeal.equals(vegMeal2));
    }
}