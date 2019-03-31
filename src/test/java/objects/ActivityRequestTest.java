package objects;

import org.junit.Test;

import static org.junit.Assert.*;

public class ActivityRequestTest {

    @Test
    public void getEmail() {
        ActivityRequest act = new ActivityRequest();
        act.setEmail("kek");
        assertTrue(act.getEmail().equals("kek"));
    }

}