package objects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.LinkedList;

@JsonIgnoreProperties
public class ActivityListResponse {
    private String email;
    private LinkedList<Activity> activities;
    private boolean activityListSuccess;


    public ActivityListResponse() {

    }

    public LinkedList<Activity> getActivities() {
        return activities;
    }

    public void setActivities(LinkedList<Activity> activities) {
        this.activities = activities;
    }



    public boolean isActivityListSuccess() {
        return activityListSuccess;
    }

    public void setActivityListSuccess(boolean activityListSuccess) {
        this.activityListSuccess = activityListSuccess;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

