package objects;

import java.util.Objects;

public class ActivityResponse {
    public boolean addActivitySuccess;

    public ActivityResponse() {
    }
    public boolean isAddActivitySuccess() {
        return addActivitySuccess;
    }
    public boolean isAddActivityResponse() {
        return addActivitySuccess;
    }

    public void setAddActivitySuccess(boolean addActivitySuccess) {
        this.addActivitySuccess = addActivitySuccess;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ActivityResponse that = (ActivityResponse) o;
        return addActivitySuccess == that.addActivitySuccess;
    }

}


