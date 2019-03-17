package objects;

import java.sql.Timestamp;
import java.util.Objects;

public class Meal {
    private Timestamp time;
    private int mealAmount;

    public Meal() {
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public int getMealAmount() {
        return mealAmount;
    }

    public void setMealAmount(int mealAmount) {
        this.mealAmount = mealAmount;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Meal meal = (Meal) obj;
        return mealAmount == meal.mealAmount
            && Objects.equals(time, meal.time);
    }
}
