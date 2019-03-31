package objects;

import java.util.LinkedList;

public class FriendListResponse {
    private String email;
    private LinkedList<String> friends;
    private boolean friendsListSuccess;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LinkedList<String> getFriends() {
        return friends;
    }

    public void setFriends(LinkedList<String> friends) {
        this.friends = friends;
    }

    public boolean isFriendsListSuccess() {
        return friendsListSuccess;
    }

    public void setFriendsListSuccess(boolean friendsListSuccess) {
        this.friendsListSuccess = friendsListSuccess;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        FriendListResponse that = (FriendListResponse) o;

        if (friendsListSuccess != that.friendsListSuccess) {
            return false;
        }
        if (email != null ? !email.equals(that.email) : that.email != null) {
            return false;
        }
        return friends != null ? friends.equals(that.friends) : that.friends == null;
    }
}
