package objects;

public class AddFriendResponse {
    private String friend2;
    private boolean addFriendSuccess;

    public AddFriendResponse() {
    }

    public String getFriend2() {
        return friend2;
    }

    public void setFriend2(String friend2) {
        this.friend2 = friend2;
    }

    public boolean isAddFriendSuccess() {
        return addFriendSuccess;
    }

    public void setAddFriendSuccess(boolean addFriendSuccess) {
        this.addFriendSuccess = addFriendSuccess;
    }
}
