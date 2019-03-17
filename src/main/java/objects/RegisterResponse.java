package objects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Objects;

@JsonIgnoreProperties
public class RegisterResponse {
    public boolean registerSuccess;
    public String name;

    public RegisterResponse(){
    }

    public boolean isRegisterSuccess() {
        return registerSuccess;
    }

    public void setRegisterSuccess(boolean registerSuccess) {
        this.registerSuccess = registerSuccess;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean getRegisterSuccess() {
        return registerSuccess;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        RegisterResponse response = (RegisterResponse) obj;
        return registerSuccess == response.registerSuccess
            && Objects.equals(name, response.name);
    }
}
