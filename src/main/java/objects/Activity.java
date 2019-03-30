package objects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.sql.Timestamp;
import java.util.Objects;

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

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Activity activity = (Activity) obj;
        return co2Amount == activity.co2Amount
            && Objects.equals(time, activity.time);
    }

    public enum ActivityObject {
        VEGMEAL,
        LOCALFOOD,
        BIKE,
        PUBTRANS,
        HOMETEMP,
        SOLARPANELS
    }
}
