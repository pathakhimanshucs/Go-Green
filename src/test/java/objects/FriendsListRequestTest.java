package objects;

import org.junit.Test;

import static org.junit.Assert.*;

public class FriendsListRequestTest {

    @Test
    public void getEmail() {
        FriendsListRequest fr = new FriendsListRequest();
        fr.setEmail("kek");
        assertTrue(fr.getEmail().equals("kek"));
    }
}