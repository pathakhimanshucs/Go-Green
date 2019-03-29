package objects;

public class AddFriendRequest {
    private AuthToken token;
    private String friend1email;
    private String friend2email;

    public AddFriendRequest() {
    }

    public String getFriend1email() {
        return friend1email;
    }

    public void setFriend1email(String friend1email) {
        this.friend1email = friend1email;
    }

    public String getFriend2email() {
        return friend2email;
    }

    public void setFriend2email(String friend2email) {
        this.friend2email = friend2email;
    }

    public AuthToken getToken() {
        return token;
    }

    public void setToken(AuthToken token) {
        this.token = token;
    }
}
