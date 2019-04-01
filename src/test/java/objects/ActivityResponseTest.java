package objects;

import org.junit.Test;

import static org.junit.Assert.*;

public class ActivityResponseTest {

    @Test
    public void isAddActivitySuccess() {
        ActivityResponse act = new ActivityResponse();
        act.setAddActivitySuccess(true);
        assertTrue(act.isAddActivitySuccess());
    }

    @Test
    public void equalsSelf() {
        ActivityResponse act = new ActivityResponse();
        act.setAddActivitySuccess(true);
        assertTrue(act.equals(act));
    }

    @Test
    public void equalsNull() {
        ActivityResponse act = new ActivityResponse();
        act.setAddActivitySuccess(true);
        assertFalse(act.equals(null));
    }

    @Test
    public void equalsElse() {
        ActivityResponse act = new ActivityResponse();
        act.setAddActivitySuccess(true);
        assertFalse(act.equals(new Friend()));
    }

    @Test
    public void equalsOther() {
        ActivityResponse act = new ActivityResponse();
        ActivityResponse actt = new ActivityResponse();
        act.setAddActivitySuccess(true);
        actt.setAddActivitySuccess(true);
        assertTrue(act.equals(actt));
    }

    @Test
    public void equalsOther2() {
        ActivityResponse act = new ActivityResponse();
        ActivityResponse actt = new ActivityResponse();
        //act.setAddActivitySuccess(true);
        //actt.setAddActivitySuccess(true);
        assertTrue(act.equals(actt));
    }

    @Test
    public void equalsOtherFalse() {
        ActivityResponse act = new ActivityResponse();
        ActivityResponse actt = new ActivityResponse();
        //act.setAddActivitySuccess(true);
        actt.setAddActivitySuccess(true);
        assertFalse(act.equals(actt));
    }

    @Test
    public void equalsOtherFalse2() {
        ActivityResponse act = new ActivityResponse();
        ActivityResponse actt = new ActivityResponse();
        act.setAddActivitySuccess(true);
        //actt.setAddActivitySuccess(true);
        assertFalse(act.equals(actt));
    }

    @Test
    public void equalsOtherFalse3() {
        ActivityResponse act = new ActivityResponse();
        ActivityResponse actt = new ActivityResponse();
        act.setAddActivitySuccess(true);
        actt.setAddActivitySuccess(false);
        assertFalse(act.equals(actt));
    }
}