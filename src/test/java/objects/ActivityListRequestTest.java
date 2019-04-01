package objects;

import org.junit.Test;

import static org.junit.Assert.*;

public class ActivityListRequestTest {

    @Test
    public void getEmail() {
        ActivityListRequest lr = new ActivityListRequest();
        lr.setEmail("kek");
        assertTrue(lr.getEmail().equals("kek"));
    }
}