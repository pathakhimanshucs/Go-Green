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
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        AuthToken authToken = (AuthToken) obj;

        if (email != null ? !email.equals(authToken.email) : authToken.email != null) {
            return false;
        }
        return token != null ? token.equals(authToken.token) : authToken.token == null;
    }
}
