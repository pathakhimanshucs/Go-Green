package objects;

import objects.LoginRequest;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LoginRequestTest {

    @Test
    public void setEmailTest(){
        LoginRequest loginReq = new LoginRequest();
        loginReq.setEmail("alice@gmail.com");
        assertEquals(loginReq.email, "alice@gmail.com");
    }

    @Test
    public void getEmailTest(){
        LoginRequest loginReq = new LoginRequest();
        loginReq.setEmail("alice@gmail.com");
        assertEquals(loginReq.getEmail(), "alice@gmail.com");
    }

    @Test
    public void setPasswordTest(){
        LoginRequest loginReq = new LoginRequest();
        loginReq.setPassword("alicepwd");
        assertEquals(loginReq.password, "alicepwd");
    }

    @Test
    public void getPasswordTest(){
        LoginRequest loginReq = new LoginRequest();
        loginReq.setPassword("alicepwd");
        assertEquals(loginReq.getPassword(), "alicepwd");
    }
}
