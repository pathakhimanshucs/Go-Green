package objects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.sql.Timestamp;

@JsonIgnoreProperties
public class Activity {
    private Timestamp time;
    private float co2Amount;
    private int amount;
    private ActivityObject activity;

    public Activity() {

    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public float getCo2Amount() {
        return co2Amount;
    }

    public void setCo2Amount(float co2Amount) {
        this.co2Amount = co2Amount;
    }

    public ActivityObject getActivity() {
        return activity;
    }

    public void setActivity(ActivityObject activity) {
        this.activity = activity;
    }

    public enum ActivityObject {
        VEGMEAL,
        LOCALFOOD,
        BIKE,
        PUBTRANS,
        HOMETEMP,
        SOLARPANELS
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }


        Activity activity1 = (Activity) o;

        if (Float.compare(activity1.co2Amount, co2Amount) != 0) {
            return false;
        }
        if (amount != activity1.amount) {
            return false;
        }
        if (time != null ? !time.equals(activity1.time) : activity1.time != null) {
            return false;
        }
        return activity == activity1.activity;
    }
    /*
    @Override
    public String toString() {



        return "";
    }
    */
}
