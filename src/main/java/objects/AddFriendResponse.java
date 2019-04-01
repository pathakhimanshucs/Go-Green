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
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        AddFriendResponse that = (AddFriendResponse) obj;

        if (addFriendSuccess != that.addFriendSuccess) {
            return false;
        }
        return friend2 != null ? friend2.equals(that.friend2) : that.friend2 == null;
    }
}
