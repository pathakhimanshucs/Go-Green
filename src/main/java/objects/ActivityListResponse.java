package objects;
import java.util.LinkedList;

public class ActivityListResponse {
    private String email;
    private LinkedList<Activity> activities;
    private boolean activityListSuccess;

    public ActivityListResponse() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LinkedList<Activity> getActivities() {
        return activities;
    }

    public void setActivities(LinkedList<Activity> meals) {
        this.activities = activities;
    }

    public void setActivityListSuccess(boolean activityListSuccess) {
        this.activityListSuccess = activityListSuccess;
    }

    /**
     * Adds meal to list or creates a new list and adds the meak.
     */
    public void addActivities(Activity act) {
        if (activities != null) {
            activities.add(act);
        } else {
            activities = new LinkedList<>();
            activities.add(act);
        }
    }

    public boolean isActivityListSuccess() {
        return activityListSuccess;
    }

    public void setMealsListSuccess(boolean mealsListSuccess) {
        this.activityListSuccess = mealsListSuccess;
    }


}

