package objects;

import java.sql.Timestamp;

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
}
