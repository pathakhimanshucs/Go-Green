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

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        ActivityListResponse that = (ActivityListResponse) obj;

        if (activityListSuccess != that.activityListSuccess) {
            return false;
        }
        if (email != null ? !email.equals(that.email) : that.email != null) {
            return false;
        }
        return activities != null ? activities.equals(that.activities) : that.activities == null;
    }

    @Override
    public int hashCode() {
        return 0;
    }
}

