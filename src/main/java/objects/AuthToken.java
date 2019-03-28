package objects;

public class AuthToken {
    private String email;
    private String token;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AuthToken authToken = (AuthToken) o;

        if (email != null ? !email.equals(authToken.email) : authToken.email != null) {
            return false;
        }
        return token != null ? token.equals(authToken.token) : authToken.token == null;
    }
}
