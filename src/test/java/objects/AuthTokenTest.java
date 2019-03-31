package objects;

import org.junit.Test;

import javax.validation.constraints.AssertTrue;

import static org.junit.Assert.*;

public class AuthTokenTest {

    @Test
    public void equalsSelf() {
        AuthToken token = new AuthToken();
        assertTrue(token.equals(token));
    }

    @Test
    public void equalsOther() {
        AuthToken token = new AuthToken();
        AuthToken token2 = new AuthToken();
        assertTrue(token.equals(token2));
    }

    @Test
    public void equalsOther2() {
        AuthToken token = new AuthToken();
        token.setEmail("kek");
        token.setToken("kek");
        AuthToken token2 = new AuthToken();
        token2.setEmail("kek");
        token2.setToken("kek");
        assertTrue(token.equals(token2));
    }

    @Test
    public void equalsOther3() {
        AuthToken token = new AuthToken();
       // token.setEmail("kek");
        token.setToken("kek");
        AuthToken token2 = new AuthToken();
        //token2.setEmail("kek");
        token2.setToken("kek");
        assertTrue(token.equals(token2));
    }

    @Test
    public void equalsOther4() {
        AuthToken token = new AuthToken();
        token.setEmail("kek");
        //token.setToken("kek");
        AuthToken token2 = new AuthToken();
        token2.setEmail("kek");
        //token2.setToken("kek");
        assertTrue(token.equals(token2));
    }

    @Test
    public void equalsOtherFalse() {
        AuthToken token = new AuthToken();
        //token.setEmail("kek");
        token.setToken("kek");
        AuthToken token2 = new AuthToken();
        token2.setEmail("kek");
        token.setToken("kek");
        assertFalse(token.equals(token2));
    }

    @Test
    public void equalsOtherFalse2() {
        AuthToken token = new AuthToken();
        token.setEmail("kek");
        //token.setToken("kek");
        AuthToken token2 = new AuthToken();
        token2.setEmail("kek");
        token.setToken("kek");
        assertFalse(token.equals(token2));
    }

    @Test
    public void equalsOtherFalse3() {
        AuthToken token = new AuthToken();
        token.setEmail("kek");
        token.setToken("kek");
        AuthToken token2 = new AuthToken();
        //token2.setEmail("kek");
        token.setToken("kek");
        assertFalse(token.equals(token2));
    }

    @Test
    public void equalsOtherFalse4() {
        AuthToken token = new AuthToken();
        token.setEmail("kek");
        token.setToken("kek");
        AuthToken token2 = new AuthToken();
        token2.setEmail("kek");
        //token.setToken("kek");
        assertFalse(token.equals(token2));
    }

    @Test
    public void equalsOtherFalse5() {
        AuthToken token = new AuthToken();
        token.setEmail("kek");
        token.setToken("kek");
        AuthToken token2 = new AuthToken();
        token2.setEmail("kek");
        token.setToken("kkek");
        assertFalse(token.equals(token2));
    }

    @Test
    public void equalsOtherFalse6() {
        AuthToken token = new AuthToken();
        token.setEmail("kek");
        token.setToken("kkek");
        AuthToken token2 = new AuthToken();
        token2.setEmail("kek");
        token.setToken("kek");
        assertFalse(token.equals(token2));
    }

    @Test
    public void equalsOtherFalse7() {
        AuthToken token = new AuthToken();
        //token.setEmail("kek");
        token.setToken("kkek");
        AuthToken token2 = new AuthToken();
        //token2.setEmail("kek");
        token.setToken("kek");
        assertFalse(token.equals(token2));
    }

    @Test
    public void equalsNull() {
        AuthToken token = new AuthToken();
        assertFalse(token.equals(null));
    }

    @Test
    public void equalsElse() {
        AuthToken token = new AuthToken();
        assertFalse(token.equals(new Activity()));
    }


}