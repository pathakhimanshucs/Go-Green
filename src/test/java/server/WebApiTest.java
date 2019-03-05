package server;

import objects.LoginRequest;
import objects.LoginResponse;
import org.junit.Test;


import static org.junit.Assert.*;

public class WebApiTest {

    @Test
    public void requestTrue() {
        WebApi api = new WebApi();
        assertTrue(api.request("World").equals("Hello World"));
    }

    @Test
    public void requestFalse() {
        WebApi api = new WebApi();
        assertFalse(api.request("").equals("Hello World"));
    }

    @Test
    public void loginError(){
        WebApi api = new WebApi();
        LoginRequest loginReq = new LoginRequest();
        loginReq.setEmail("error@gmail.com");
        loginReq.setPassword("wrongpassword");
        LoginResponse loginRes = new LoginResponse();
        loginRes.setName("error");
        assertEquals(api.login(loginReq).getName(),loginRes.getName());
    }

    @Test
    public void loginSuccess(){
        WebApi api = new WebApi();
        LoginRequest loginReq = new LoginRequest();
        loginReq.setEmail("alice@gmail.com");
        loginReq.setPassword("alicepwd");
        LoginResponse loginRes = new LoginResponse();
        loginRes.setName("Alice");
        assertEquals(api.login(loginReq).getName(),loginRes.getName());
    }
}