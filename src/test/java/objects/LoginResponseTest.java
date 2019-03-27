package objects;

import objects.LoginResponse;
import org.junit.Test;

import static org.junit.Assert.*;

public class LoginResponseTest {

    @Test
    public void setNameTest(){
        LoginResponse loginRes = new LoginResponse();
        loginRes.setName("Alice");
        assertEquals(loginRes.getName(), "Alice");
    }

    @Test
    public void getNameTest(){
        LoginResponse loginRes = new LoginResponse();
        loginRes.setName("Alice");
        assertEquals(loginRes.getName(), "Alice");
    }

    @Test
    public void equalsSelf(){
        LoginResponse loginRes = new LoginResponse();
        loginRes.setName("Alice");
        assertTrue(loginRes.equals(loginRes));
    }

    @Test
    public void equalsNull(){
        LoginResponse loginRes = new LoginResponse();
        loginRes.setName("Alice");
        assertFalse(loginRes.equals(null));
    }

    @Test
    public void equalsElse(){
        LoginResponse loginRes = new LoginResponse();
        loginRes.setName("Alice");
        LoginRequest loginReq = new LoginRequest();
        assertFalse(loginRes.equals(loginReq));
    }
}
