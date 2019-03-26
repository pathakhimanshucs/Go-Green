package objects;

public class ActivityRequest {

    String email;
    int amount;
    Activity activity;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAmount() {
        return amount;
    }
    public Activity getActivity(){
        return activity;
    }
    public void setAmount(int amount) {
        this.amount = amount;
    }
}

