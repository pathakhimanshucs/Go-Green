package Server;

import org.junit.Test;


import javax.validation.constraints.AssertTrue;

import static org.junit.Assert.*;

public class WebAPITest {

    @Test
    public void requestTrue()
    {
        WebAPI api = new WebAPI();
        assertTrue(api.Request("World").equals("Hello World"));
    }

    @Test
    public void requestFalse()
    {
        WebAPI api = new WebAPI();
        assertFalse(api.Request("").equals("Hello World"));
    }
}