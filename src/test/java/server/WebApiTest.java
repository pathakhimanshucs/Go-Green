package server;

import org.junit.Test;


import java.sql.SQLException;
import java.util.HashMap;

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
        HashMap<String, String> response = new HashMap<>();
        response.put("name", "error");
        assertEquals(api.login("", ""),response);
    }

    @Test
    public void loginSuccess(){
        WebApi api = new WebApi();
        HashMap<String, String> response = new HashMap<>();
        response.put("name", "Alice");
        assertEquals(api.login("alice@gmail.com", "alicepwd"),response);
    }
}