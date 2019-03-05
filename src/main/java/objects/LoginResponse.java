package objects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Objects;

@JsonIgnoreProperties
public class LoginResponse {
    public String name;

    public LoginResponse(){
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

