package objects;

import org.junit.Test;

import static org.junit.Assert.*;

public class RegisterRequestTest {

    @Test
    public void getName() {
        RegisterRequest regReq = new RegisterRequest();
        regReq.setName("bla");
        assertEquals(regReq.getName(), "bla");
    }

    @Test
    public void getEmail() {
        RegisterRequest regReq = new RegisterRequest();
        regReq.setEmail("bla@gmail.com");
        assertEquals(regReq.getEmail(), "bla@gmail.com");
    }

    @Test
    public void getPassword() {
        RegisterRequest regReq = new RegisterRequest();
        regReq.setPassword("pwd");
        assertEquals(regReq.getPassword(), "pwd");
    }

}
