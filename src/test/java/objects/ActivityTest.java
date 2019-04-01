package objects;

import org.junit.Test;

import javax.validation.constraints.AssertTrue;
import java.sql.Time;
import java.sql.Timestamp;

import static org.junit.Assert.*;

public class ActivityTest {

    @Test
    public void getTime() {
        Timestamp time = new Timestamp(420);
        Activity act = new Activity();
        act.setTime(time);
        assertTrue(act.getTime() == time);
    }

    @Test
    public void equalsSelf() {
        Activity act = new Activity();
        assertTrue(act.equals(act));
    }

    @Test
    public void equalsNull() {
        Activity act = new Activity();
        assertFalse(act.equals(null));
    }

    @Test
    public void equalsElse() {
        Activity act = new Activity();
        assertFalse(act.equals(new Friend()));
    }

    @Test
    public void equalsOther() {
        Activity act = new Activity();
        Activity actt = new Activity();
        act.setAmount(1);
        actt.setAmount(1);
        act.setActivity(Activity.ActivityObject.BIKE);
        actt.setActivity(Activity.ActivityObject.BIKE);
        act.setCo2Amount(1);
        actt.setCo2Amount(1);
        Timestamp time = new Timestamp(420);
        act.setTime(time);
        actt.setTime(time);
        assertTrue(act.equals(actt));
    }

    @Test
    public void equalsOther2() {
        Activity act = new Activity();
        Activity actt = new Activity();
        act.setAmount(1);
        actt.setAmount(1);
        act.setActivity(Activity.ActivityObject.BIKE);
        actt.setActivity(Activity.ActivityObject.BIKE);
        act.setCo2Amount(1);
        actt.setCo2Amount(1);
        Timestamp time = new Timestamp(420);
        //act.setTime(time);
        //actt.setTime(time);
        assertTrue(act.equals(actt));
    }

    @Test
    public void equalsOtherFalse() {
        Activity act = new Activity();
        Activity actt = new Activity();
        //act.setAmount(1);
        actt.setAmount(1);
        act.setActivity(Activity.ActivityObject.BIKE);
        actt.setActivity(Activity.ActivityObject.BIKE);
        act.setCo2Amount(1);
        actt.setCo2Amount(1);
        Timestamp time = new Timestamp(420);
        act.setTime(time);
        actt.setTime(time);
        assertFalse(act.equals(actt));
    }

    @Test
    public void equalsOtherFalse2() {
        Activity act = new Activity();
        Activity actt = new Activity();
        act.setAmount(1);
       // actt.setAmount(1);
        act.setActivity(Activity.ActivityObject.BIKE);
        actt.setActivity(Activity.ActivityObject.BIKE);
        act.setCo2Amount(1);
        actt.setCo2Amount(1);
        Timestamp time = new Timestamp(420);
        act.setTime(time);
        actt.setTime(time);
        assertFalse(act.equals(actt));
    }

    @Test
    public void equalsOtherFalse3() {
        Activity act = new Activity();
        Activity actt = new Activity();
        act.setAmount(1);
        actt.setAmount(1);
       // act.setActivity(Activity.ActivityObject.BIKE);
        actt.setActivity(Activity.ActivityObject.BIKE);
        act.setCo2Amount(1);
        actt.setCo2Amount(1);
        Timestamp time = new Timestamp(420);
        act.setTime(time);
        actt.setTime(time);
        assertFalse(act.equals(actt));
    }

    @Test
    public void equalsOtherFalse4() {
        Activity act = new Activity();
        Activity actt = new Activity();
        act.setAmount(1);
        actt.setAmount(1);
        act.setActivity(Activity.ActivityObject.BIKE);
        //actt.setActivity(Activity.ActivityObject.BIKE);
        act.setCo2Amount(1);
        actt.setCo2Amount(1);
        Timestamp time = new Timestamp(420);
        act.setTime(time);
        actt.setTime(time);
        assertFalse(act.equals(actt));
    }

    @Test
    public void equalsOtherFalse5() {
        Activity act = new Activity();
        Activity actt = new Activity();
        act.setAmount(1);
        actt.setAmount(1);
        act.setActivity(Activity.ActivityObject.BIKE);
        actt.setActivity(Activity.ActivityObject.BIKE);
        //act.setCo2Amount(1);
        actt.setCo2Amount(1);
        Timestamp time = new Timestamp(420);
        act.setTime(time);
        actt.setTime(time);
        assertFalse(act.equals(actt));
    }

    @Test
    public void equalsOtherFalse6() {
        Activity act = new Activity();
        Activity actt = new Activity();
        act.setAmount(1);
        actt.setAmount(1);
        act.setActivity(Activity.ActivityObject.BIKE);
        actt.setActivity(Activity.ActivityObject.BIKE);
        act.setCo2Amount(1);
        //actt.setCo2Amount(1);
        Timestamp time = new Timestamp(420);
        act.setTime(time);
        actt.setTime(time);
        assertFalse(act.equals(actt));
    }

    @Test
    public void equalsOtherFalse7() {
        Activity act = new Activity();
        Activity actt = new Activity();
        act.setAmount(1);
        actt.setAmount(1);
        act.setActivity(Activity.ActivityObject.BIKE);
        actt.setActivity(Activity.ActivityObject.BIKE);
        act.setCo2Amount(1);
        actt.setCo2Amount(1);
        Timestamp time = new Timestamp(420);
        //act.setTime(time);
        actt.setTime(time);
        assertFalse(act.equals(actt));
    }

    @Test
    public void equalsOtherFalse8() {
        Activity act = new Activity();
        Activity actt = new Activity();
        act.setAmount(1);
        actt.setAmount(1);
        act.setActivity(Activity.ActivityObject.BIKE);
        actt.setActivity(Activity.ActivityObject.BIKE);
        act.setCo2Amount(1);
        actt.setCo2Amount(1);
        Timestamp time = new Timestamp(420);
        act.setTime(time);
        //actt.setTime(time);
        assertFalse(act.equals(actt));
    }

    @Test
    public void equalsOtherFalse9() {
        Activity act = new Activity();
        Activity actt = new Activity();
        act.setAmount(1);
        actt.setAmount(1);
        act.setActivity(Activity.ActivityObject.BIKE);
        actt.setActivity(Activity.ActivityObject.VEGMEAL);
        act.setCo2Amount(1);
        actt.setCo2Amount(1);
        Timestamp time = new Timestamp(420);
        act.setTime(time);
        actt.setTime(time);
        assertFalse(act.equals(actt));
    }

    @Test
    public void equalsOtherFalse10() {
        Activity act = new Activity();
        Activity actt = new Activity();
        act.setAmount(1);
        actt.setAmount(1);
        act.setActivity(Activity.ActivityObject.BIKE);
        actt.setActivity(Activity.ActivityObject.BIKE);
        act.setCo2Amount(1);
        actt.setCo2Amount(1);
        Timestamp time = new Timestamp(420);
        act.setTime(time);
        Timestamp time2 = new Timestamp(69);
        actt.setTime(time2);
        assertFalse(act.equals(actt));
    }
}