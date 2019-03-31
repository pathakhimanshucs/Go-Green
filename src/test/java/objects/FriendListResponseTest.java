package objects;

import org.junit.Test;
import sun.awt.image.ImageWatched;

import javax.validation.constraints.AssertTrue;
import java.util.LinkedList;

import static org.junit.Assert.*;

public class FriendListResponseTest {

    @Test
    public void getEmail() {
        FriendListResponse fr = new FriendListResponse();
        fr.setEmail("kek");
        assertTrue(fr.getEmail().equals("kek"));
    }

    @Test
    public void getFriends() {
        FriendListResponse fr = new FriendListResponse();
        LinkedList<Friend> fl = new LinkedList<>();
        fr.setFriends(fl);
        assertTrue(fr.getFriends() == fl);
    }

    @Test
    public void isFriendsListSuccess() {
        FriendListResponse fr = new FriendListResponse();
        fr.setFriendsListSuccess(true);
        assertTrue(fr.isFriendsListSuccess());
    }

    @Test
    public void equalsOther() {
        FriendListResponse fr = new FriendListResponse();
        FriendListResponse frr = new FriendListResponse();
        fr.setEmail("kek");
        frr.setEmail("kek");
        fr.setFriendsListSuccess(true);
        frr.setFriendsListSuccess(true);
        LinkedList<Friend> fl = new LinkedList<>();
        fr.setFriends(fl);
        frr.setFriends(fl);
        assertTrue(fr.equals(frr));
    }

    @Test
    public void equalsOther2() {
        FriendListResponse fr = new FriendListResponse();
        FriendListResponse frr = new FriendListResponse();
        //fr.setEmail("kek");
        //frr.setEmail("kek");
        fr.setFriendsListSuccess(true);
        frr.setFriendsListSuccess(true);
        LinkedList<Friend> fl = new LinkedList<>();
        fr.setFriends(fl);
        frr.setFriends(fl);
        assertTrue(fr.equals(frr));
    }

    @Test
    public void equalsOther3() {
        FriendListResponse fr = new FriendListResponse();
        FriendListResponse frr = new FriendListResponse();
        fr.setEmail("kek");
        frr.setEmail("kek");
        //fr.setFriendsListSuccess(true);
        //frr.setFriendsListSuccess(true);
        LinkedList<Friend> fl = new LinkedList<>();
        fr.setFriends(fl);
        frr.setFriends(fl);
        assertTrue(fr.equals(frr));
    }

    @Test
    public void equalsOther4() {
        FriendListResponse fr = new FriendListResponse();
        FriendListResponse frr = new FriendListResponse();
        fr.setEmail("kek");
        frr.setEmail("kek");
        fr.setFriendsListSuccess(true);
        frr.setFriendsListSuccess(true);
        LinkedList<Friend> fl = new LinkedList<>();
        //fr.setFriends(fl);
        //frr.setFriends(fl);
        assertTrue(fr.equals(frr));
    }

    @Test
    public void equalsOtherFalse() {
        FriendListResponse fr = new FriendListResponse();
        FriendListResponse frr = new FriendListResponse();
        //fr.setEmail("kek");
        frr.setEmail("kek");
        fr.setFriendsListSuccess(true);
        frr.setFriendsListSuccess(true);
        LinkedList<Friend> fl = new LinkedList<>();
        fr.setFriends(fl);
        frr.setFriends(fl);
        assertFalse(fr.equals(frr));
    }

    @Test
    public void equalsOtherFalse2() {
        FriendListResponse fr = new FriendListResponse();
        FriendListResponse frr = new FriendListResponse();
        fr.setEmail("kek");
        //frr.setEmail("kek");
        fr.setFriendsListSuccess(true);
        frr.setFriendsListSuccess(true);
        LinkedList<Friend> fl = new LinkedList<>();
        fr.setFriends(fl);
        frr.setFriends(fl);
        assertFalse(fr.equals(frr));
    }

    @Test
    public void equalsOtherFalse3() {
        FriendListResponse fr = new FriendListResponse();
        FriendListResponse frr = new FriendListResponse();
        fr.setEmail("kek");
        frr.setEmail("kek");
        //fr.setFriendsListSuccess(true);
        frr.setFriendsListSuccess(true);
        LinkedList<Friend> fl = new LinkedList<>();
        fr.setFriends(fl);
        frr.setFriends(fl);
        assertFalse(fr.equals(frr));
    }

    @Test
    public void equalsOtherFalse4() {
        FriendListResponse fr = new FriendListResponse();
        FriendListResponse frr = new FriendListResponse();
        fr.setEmail("kek");
        frr.setEmail("kek");
        fr.setFriendsListSuccess(true);
        //frr.setFriendsListSuccess(true);
        LinkedList<Friend> fl = new LinkedList<>();
        fr.setFriends(fl);
        frr.setFriends(fl);
        assertFalse(fr.equals(frr));
    }

    @Test
    public void equalsOtherFalse5() {
        FriendListResponse fr = new FriendListResponse();
        FriendListResponse frr = new FriendListResponse();
        fr.setEmail("kek");
        frr.setEmail("kek");
        fr.setFriendsListSuccess(true);
        frr.setFriendsListSuccess(true);
        LinkedList<Friend> fl = new LinkedList<>();
        //fr.setFriends(fl);
        frr.setFriends(fl);
        assertFalse(fr.equals(frr));
    }

    @Test
    public void equalsOtherFalse6() {
        FriendListResponse fr = new FriendListResponse();
        FriendListResponse frr = new FriendListResponse();
        fr.setEmail("kek");
        frr.setEmail("kek");
        fr.setFriendsListSuccess(true);
        frr.setFriendsListSuccess(true);
        LinkedList<Friend> fl = new LinkedList<>();
        fr.setFriends(fl);
        //frr.setFriends(fl);
        assertFalse(fr.equals(frr));
    }

    @Test
    public void equalsSelf() {
        FriendListResponse fr = new FriendListResponse();
        FriendListResponse frr = new FriendListResponse();
        fr.setEmail("kek");
        frr.setEmail("kek");
        fr.setFriendsListSuccess(true);
        frr.setFriendsListSuccess(true);
        LinkedList<Friend> fl = new LinkedList<>();
        fr.setFriends(fl);
        frr.setFriends(fl);
        assertTrue(fr.equals(fr));
    }

    @Test
    public void equalsNull() {
        FriendListResponse fr = new FriendListResponse();
        FriendListResponse frr = new FriendListResponse();
        fr.setEmail("kek");
        frr.setEmail("kek");
        fr.setFriendsListSuccess(true);
        frr.setFriendsListSuccess(true);
        LinkedList<Friend> fl = new LinkedList<>();
        fr.setFriends(fl);
        frr.setFriends(fl);
        assertFalse(fr.equals(null));
    }

    @Test
    public void equalsElse() {
        FriendListResponse fr = new FriendListResponse();
        FriendListResponse frr = new FriendListResponse();
        fr.setEmail("kek");
        frr.setEmail("kek");
        fr.setFriendsListSuccess(true);
        frr.setFriendsListSuccess(true);
        LinkedList<Friend> fl = new LinkedList<>();
        fr.setFriends(fl);
        frr.setFriends(fl);
        assertFalse(fr.equals(new Activity()));
    }


}