package objects;

import org.junit.Test;

import javax.validation.constraints.AssertTrue;

import java.util.LinkedList;

import static org.junit.Assert.*;

public class ActivityListResponseTest {

    @Test
    public void getActivities() {
        ActivityListResponse list = new ActivityListResponse();
        assertTrue(list.getActivities() == null);
    }

    @Test
    public void isActivityListSuccess() {
        ActivityListResponse list = new ActivityListResponse();
        list.setActivityListSuccess(true);
        assertTrue(list.isActivityListSuccess());
    }

    @Test
    public void getEmail() {
        ActivityListResponse list = new ActivityListResponse();
        list.setEmail("kek");
        assertTrue(list.getEmail().equals("kek"));
    }

    @Test
    public void hashCodetest() {
        ActivityListResponse list = new ActivityListResponse();
        assertTrue(list.hashCode() == 0);
    }

    @Test
    public void equalsSelf() {
        ActivityListResponse list = new ActivityListResponse();
        assertTrue(list.equals(list));
    }

    @Test
    public void equalsElse() {
        ActivityListResponse list = new ActivityListResponse();
        assertFalse(list.equals(new Activity()));
    }

    @Test
    public void equalsNull() {
        ActivityListResponse list = new ActivityListResponse();
        assertFalse(list.equals(null));
    }

    @Test
    public void equalsOther() {
        ActivityListResponse list = new ActivityListResponse();
        ActivityListResponse list2 = new ActivityListResponse();
        list.setActivityListSuccess(false);
        list2.setActivityListSuccess(false);
        list.setEmail("kek");
        list2.setEmail("kek");
        LinkedList<Activity> lamelist = new LinkedList<>();
        list.setActivities(lamelist);
        list2.setActivities(lamelist);
        assertTrue(list.equals(list2));
    }

    @Test
    public void equalsOther2() {
        ActivityListResponse list = new ActivityListResponse();
        ActivityListResponse list2 = new ActivityListResponse();
        list.setActivityListSuccess(false);
        list2.setActivityListSuccess(false);
        //list.setEmail("kek");
        //list2.setEmail("kek");
        LinkedList<Activity> lamelist = new LinkedList<>();
        list.setActivities(lamelist);
        list2.setActivities(lamelist);
        assertTrue(list.equals(list2));
    }

    @Test
    public void equalsFalse() {
        ActivityListResponse list = new ActivityListResponse();
        ActivityListResponse list2 = new ActivityListResponse();
        //list.setActivityListSuccess(true);
        list2.setActivityListSuccess(true);
        list.setEmail("kek");
        list2.setEmail("kek");
        LinkedList<Activity> lamelist = new LinkedList<>();
        list.setActivities(lamelist);
        list2.setActivities(lamelist);
        assertFalse(list.equals(list2));
    }

    @Test
    public void equalsFalse2() {
        ActivityListResponse list = new ActivityListResponse();
        ActivityListResponse list2 = new ActivityListResponse();
        list.setActivityListSuccess(true);
        //list2.setActivityListSuccess(true);
        list.setEmail("kek");
        list2.setEmail("kek");
        LinkedList<Activity> lamelist = new LinkedList<>();
        list.setActivities(lamelist);
        list2.setActivities(lamelist);
        assertFalse(list.equals(list2));
    }

    @Test
    public void equalsFalse3() {
        ActivityListResponse list = new ActivityListResponse();
        ActivityListResponse list2 = new ActivityListResponse();
        list.setActivityListSuccess(false);
        list2.setActivityListSuccess(false);
        //list.setEmail("kek");
        list2.setEmail("kek");
        LinkedList<Activity> lamelist = new LinkedList<>();
        list.setActivities(lamelist);
        list2.setActivities(lamelist);
        assertFalse(list.equals(list2));
    }

    @Test
    public void equalsFalse4() {
        ActivityListResponse list = new ActivityListResponse();
        ActivityListResponse list2 = new ActivityListResponse();
        list.setActivityListSuccess(false);
        list2.setActivityListSuccess(false);
        list.setEmail("kek");
        //list2.setEmail("kek");
        LinkedList<Activity> lamelist = new LinkedList<>();
        list.setActivities(lamelist);
        list2.setActivities(lamelist);
        assertFalse(list.equals(list2));
    }

    @Test
    public void equalsFalse5() {
        ActivityListResponse list = new ActivityListResponse();
        ActivityListResponse list2 = new ActivityListResponse();
        list.setActivityListSuccess(false);
        list2.setActivityListSuccess(false);
        list.setEmail("kek");
        list2.setEmail("kek");
        LinkedList<Activity> lamelist = new LinkedList<>();
        //list.setActivities(lamelist);
        list2.setActivities(lamelist);
        assertFalse(list.equals(list2));
    }

    @Test
    public void equalsFalse6() {
        ActivityListResponse list = new ActivityListResponse();
        ActivityListResponse list2 = new ActivityListResponse();
        list.setActivityListSuccess(false);
        list2.setActivityListSuccess(false);
        list.setEmail("kek");
        list2.setEmail("kek");
        LinkedList<Activity> lamelist = new LinkedList<>();
        list.setActivities(lamelist);
       //list2.setActivities(lamelist);
        assertFalse(list.equals(list2));
    }

    @Test
    public void equalsFalse7() {
        ActivityListResponse list = new ActivityListResponse();
        ActivityListResponse list2 = new ActivityListResponse();
        list.setActivityListSuccess(false);
        list2.setActivityListSuccess(false);
        list.setEmail("kek");
        list2.setEmail("keok");
        LinkedList<Activity> lamelist = new LinkedList<>();
        list.setActivities(lamelist);
        list2.setActivities(lamelist);
        assertFalse(list.equals(list2));
    }
}