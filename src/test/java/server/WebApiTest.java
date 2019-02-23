package server;

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
}