package objects;

import org.junit.Test;

import javax.validation.constraints.AssertTrue;

import static org.junit.Assert.*;

public class AddFriendResponseTest {

    @Test
    public void getFriend2() {
        AddFriendResponse fr = new AddFriendResponse();
        fr.setFriend2("kek");
        assertTrue(fr.getFriend2().equals("kek"));
    }

    @Test
    public void isAddFriendSuccess() {
        AddFriendResponse fr = new AddFriendResponse();
        fr.setAddFriendSuccess(true);
        assertTrue(fr.isAddFriendSuccess());
    }

    @Test
    public void equalsSelf() {
        AddFriendResponse fr = new AddFriendResponse();
        assertTrue(fr.equals(fr));
    }

    @Test
    public void equalsNull() {
        AddFriendResponse fr = new AddFriendResponse();
        assertFalse(fr.equals(null));
    }

    @Test
    public void equalsElse() {
        AddFriendResponse fr = new AddFriendResponse();
        assertFalse(fr.equals(new Activity()));
    }

    @Test
    public void equalsOther() {
        AddFriendResponse fr = new AddFriendResponse();
        AddFriendResponse frr = new AddFriendResponse();
        fr.setAddFriendSuccess(true);
        frr.setAddFriendSuccess(true);
        fr.setFriend2("kek");
        frr.setFriend2("kek");
        assertTrue(fr.equals(frr));
    }

    @Test
    public void equalsOtherFalse() {
        AddFriendResponse fr = new AddFriendResponse();
        AddFriendResponse frr = new AddFriendResponse();
        //fr.setAddFriendSuccess(true);
        frr.setAddFriendSuccess(true);
        fr.setFriend2("kek");
        frr.setFriend2("kek");
        assertFalse(fr.equals(frr));
    }

    @Test
    public void equalsOtherFalse2() {
        AddFriendResponse fr = new AddFriendResponse();
        AddFriendResponse frr = new AddFriendResponse();
        fr.setAddFriendSuccess(true);
        //frr.setAddFriendSuccess(true);
        fr.setFriend2("kek");
        frr.setFriend2("kek");
        assertFalse(fr.equals(frr));
    }

    @Test
    public void equalsOtherFalse3() {
        AddFriendResponse fr = new AddFriendResponse();
        AddFriendResponse frr = new AddFriendResponse();
        fr.setAddFriendSuccess(true);
        frr.setAddFriendSuccess(true);
        //fr.setFriend2("kek");
        frr.setFriend2("kek");
        assertFalse(fr.equals(frr));
    }

    @Test
    public void equalsOtherFalse4() {
        AddFriendResponse fr = new AddFriendResponse();
        AddFriendResponse frr = new AddFriendResponse();
        fr.setAddFriendSuccess(true);
        frr.setAddFriendSuccess(true);
        fr.setFriend2("kek");
        //frr.setFriend2("kek");
        assertFalse(fr.equals(frr));
    }

    @Test
    public void equalsOtherFalse5() {
        AddFriendResponse fr = new AddFriendResponse();
        AddFriendResponse frr = new AddFriendResponse();
        fr.setAddFriendSuccess(true);
        frr.setAddFriendSuccess(true);
        fr.setFriend2("kek");
        frr.setFriend2("kkek");
        assertFalse(fr.equals(frr));
    }
}