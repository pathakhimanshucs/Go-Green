package objects;

import org.junit.Test;

import static org.junit.Assert.*;

public class RegisterResponseTest {

    @Test
    public void getName() {
        RegisterResponse regRes = new RegisterResponse();
        regRes.setName("kek");
        assertEquals(regRes.getName(), "kek");
    }

    @Test
    public void getToken() {
        RegisterResponse regRes = new RegisterResponse();
        AuthToken at = new AuthToken();
        regRes.setToken(at);
        assertTrue(regRes.getToken() == at);
    }

    @Test
    public void getRegisterSuccess() {
        RegisterResponse regRes = new RegisterResponse();
        regRes.setRegisterSuccess(true);
        assertEquals(regRes.getRegisterSuccess(), true);
    }

    @Test
    public void equalsTrue() {
        RegisterResponse regRes = new RegisterResponse();
        RegisterResponse regRes2 = new RegisterResponse();
        regRes.setRegisterSuccess(true);
        regRes.setName("kek");
        regRes2.setRegisterSuccess(true);
        regRes2.setName("kek");
        assertTrue(regRes.equals(regRes2));
    }

    @Test
    public void equalsFalse() {
        RegisterResponse regRes = new RegisterResponse();
        RegisterResponse regRes2 = new RegisterResponse();
        regRes.setRegisterSuccess(true);
        regRes.setName("top");
        regRes2.setRegisterSuccess(true);
        regRes2.setName("kek");
        assertFalse(regRes.equals(regRes2));
    }

    @Test
    public void equalsFalse2() {
        RegisterResponse regRes = new RegisterResponse();
        RegisterResponse regRes2 = new RegisterResponse();
        regRes.setRegisterSuccess(true);
        regRes.setName("kek");
        regRes2.setRegisterSuccess(false);
        regRes2.setName("kek");
        assertFalse(regRes.equals(regRes2));
    }

    @Test
    public void equalsSelf() {
        RegisterResponse regRes = new RegisterResponse();
        regRes.setRegisterSuccess(true);
        regRes.setName("kek");
        assertTrue(regRes.equals(regRes));
    }

    @Test
    public void equalsNull() {
        RegisterResponse regRes = new RegisterResponse();
        regRes.setRegisterSuccess(true);
        regRes.setName("kek");
        assertFalse(regRes.equals(null));
    }

    @Test
    public void equalsElse() {
        RegisterResponse regRes = new RegisterResponse();
        regRes.setRegisterSuccess(true);
        regRes.setName("kek");
        LoginResponse response = new LoginResponse();
        assertFalse(regRes.equals(response));
    }
}