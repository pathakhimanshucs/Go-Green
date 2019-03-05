package objects;

import objects.LoginResponse;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LoginResponseTest {

    @Test
    public void setNameTest(){
        LoginResponse loginRes = new LoginResponse();
        loginRes.setName("Alice");
        assertEquals(loginRes.name, "Alice");
    }

    @Test
    public void getNameTest(){
        LoginResponse loginRes = new LoginResponse();
        loginRes.setName("Alice");
        assertEquals(loginRes.getName(), "Alice");
    }
}
