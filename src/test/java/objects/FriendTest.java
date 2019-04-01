package objects;

import org.junit.Test;

import static org.junit.Assert.*;

public class FriendTest {

    @Test
    public void getEmail() {
        Friend fr = new Friend();
        fr.setEmail("kek");
        assertTrue(fr.getEmail().equals("kek"));
    }

    @Test
    public void getTotalCO2() {
        Friend fr = new Friend();
        fr.setTotalCO2(1);
        assertTrue(fr.getTotalCO2() == 1);
    }

    @Test
    public void equalsSelf() {
        Friend fr = new Friend();
        fr.setEmail("kek");
        assertTrue(fr.equals(fr));
    }

    @Test
    public void equalsNull() {
        Friend fr = new Friend();
        fr.setEmail("kek");
        assertFalse(fr.equals(null));
    }

    @Test
    public void equalsElse() {
        Friend fr = new Friend();
        fr.setEmail("kek");
        assertFalse(fr.equals(new Activity()));
    }

    @Test
    public void equalsOther() {
        Friend fr = new Friend();
        Friend frr = new Friend();
        fr.setEmail("kek");
        frr.setEmail("kek");
        fr.setTotalCO2(1);
        frr.setTotalCO2(1);
        assertTrue(fr.equals(frr));
    }

    @Test
    public void equalsOther2() {
        Friend fr = new Friend();
        Friend frr = new Friend();
        //fr.setEmail("kek");
        //frr.setEmail("kek");
        fr.setTotalCO2(1);
        frr.setTotalCO2(1);
        assertTrue(fr.equals(frr));
    }

    @Test
    public void equalsOther3() {
        Friend fr = new Friend();
        Friend frr = new Friend();
        fr.setEmail("kek");
        frr.setEmail("kek");
        //fr.setTotalCO2(1);
        //frr.setTotalCO2(1);
        assertTrue(fr.equals(frr));
    }

    @Test
    public void equalsOtherFalse() {
        Friend fr = new Friend();
        Friend frr = new Friend();
        //fr.setEmail("kek");
        frr.setEmail("kek");
        fr.setTotalCO2(1);
        frr.setTotalCO2(1);
        assertFalse(fr.equals(frr));
    }

    @Test
    public void equalsOtherFalse2() {
        Friend fr = new Friend();
        Friend frr = new Friend();
        fr.setEmail("kek");
        //frr.setEmail("kek");
        fr.setTotalCO2(1);
        frr.setTotalCO2(1);
        assertFalse(fr.equals(frr));
    }

    @Test
    public void equalsOtherFalse3() {
        Friend fr = new Friend();
        Friend frr = new Friend();
        fr.setEmail("kek");
        frr.setEmail("kek");
        //fr.setTotalCO2(1);
        frr.setTotalCO2(1);
        assertFalse(fr.equals(frr));
    }

    @Test
    public void equalsOtherFalse4() {
        Friend fr = new Friend();
        Friend frr = new Friend();
        fr.setEmail("kek");
        frr.setEmail("kek");
        fr.setTotalCO2(1);
        //frr.setTotalCO2(1);
        assertFalse(fr.equals(frr));
    }
}