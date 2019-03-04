package client;

import org.junit.Test;

import javax.validation.constraints.AssertTrue;

import static org.junit.Assert.*;

public class LoginTest {

    @Test
    public void setNameTest() {
        Login login = new Login();
        login.setName("Alice");

        assertTrue(login.name.equals("Alice"));
    }

    @Test
    public void getNameTest() {
        Login login = new Login();
        login.setName("Alice");
        assertTrue(login.getName().equals("Alice"));
    }

    @Test
    public void toStringTest() {
        Login login = new Login();
        login.setName("Bob");
        assertTrue(login.toString().equals("Bob"));
    }

    @Test
    public void equalsFalse() {
        Login login1 = new Login();
        login1.setName("Bob");
        Login login2 = new Login();
        login2.setName("Alice");
        assertFalse(login1.equals(login2));
    }

    @Test
    public void equalsTrue() {
        Login login1 = new Login();
        login1.setName("Alice");
        Login login2 = new Login();
        login2.setName("Alice");
        assertTrue(login1.equals(login2));
    }
}