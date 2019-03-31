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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AddFriendResponse that = (AddFriendResponse) o;

        if (addFriendSuccess != that.addFriendSuccess) {
            return false;
        }
        return friend2 != null ? friend2.equals(that.friend2) : that.friend2 == null;
    }
}
