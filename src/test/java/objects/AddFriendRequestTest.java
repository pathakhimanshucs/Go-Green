package objects;

import org.junit.Test;

import static org.junit.Assert.*;

public class AddFriendRequestTest {

    @Test
    public void getFriend1email() {
        AddFriendRequest fr = new AddFriendRequest();
        fr.setFriend1email("kek");
        assertTrue(fr.getFriend1email().equals("kek"));
    }
}