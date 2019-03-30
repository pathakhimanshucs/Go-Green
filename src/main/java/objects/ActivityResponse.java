package objects;

public class ActivityResponse {
    public boolean addActivitySuccess;

    public ActivityResponse() {
    }

    public boolean isAddActivitySuccess() {
        return addActivitySuccess;
    }

    public void setAddActivitySuccess(boolean addActivitySuccess) {
        this.addActivitySuccess = addActivitySuccess;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ActivityResponse that = (ActivityResponse) obj;
        return addActivitySuccess == that.addActivitySuccess;
    }

}


