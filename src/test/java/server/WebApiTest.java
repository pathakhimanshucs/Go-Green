package server;

import objects.LoginRequest;
import objects.LoginResponse;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;


import javax.validation.constraints.AssertTrue;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class WebApiTest {

    @InjectMocks
    WebApi webApi;

    @Mock
    JdbcTemplate jdbcTemplate;

    @Test
    public void requestTrue() {
        WebApi api = new WebApi();
        assertEquals("Hello World", api.request("World"));
    }

    @Test
    public void requestFalse() {
        WebApi api = new WebApi();
        assertFalse(api.request("").equals("Hello World"));
    }

    @Test
    public void testAttemptLoginSuccess(){
        SqlRowSet sqlRowSet = Mockito.mock(SqlRowSet.class);
        Mockito.doReturn(true).when(sqlRowSet).next();
        Mockito.doReturn("Alice").when(sqlRowSet).getString("name");
        Mockito.doReturn(sqlRowSet).when(jdbcTemplate).queryForRowSet(Mockito.anyString(), Mockito.anyString(),Mockito.anyString());
        Assert.assertEquals(webApi.attemptLogin("alice@gmail.com", "alicepwd"), "Alice");
    }

    @Test
    public void testAttemptLoginFail(){
        SqlRowSet sqlRowSet = Mockito.mock(SqlRowSet.class);
        Mockito.doReturn(false).when(sqlRowSet).next();
        Mockito.doReturn(sqlRowSet).when(jdbcTemplate).queryForRowSet(Mockito.anyString(), Mockito.anyString(),Mockito.anyString());
        Assert.assertEquals(webApi.attemptLogin("wrong@gmail.com", "wrongpwd"), null);
    }

    @Test
    public void loginMappingSuccessTest() {
        //CheckIfEmailExists
        SqlRowSet sqlRowSet = Mockito.mock(SqlRowSet.class);
        Mockito.doReturn(sqlRowSet).when(jdbcTemplate).queryForRowSet("SELECT * FROM users WHERE email = ?", "alice@gmail.com");
        Mockito.doReturn(true).when(sqlRowSet).isBeforeFirst();
        Mockito.doReturn(true).when(sqlRowSet).next();

        //AttemptLogin
        SqlRowSet sqlRowSet1 = Mockito.mock(SqlRowSet.class);
        Mockito.doReturn(sqlRowSet1).when(jdbcTemplate).queryForRowSet("SELECT * FROM users WHERE email = ? AND password = ?", "alice@gmail.com", "alicepwd");
        Mockito.doReturn(true).when(sqlRowSet1).next();
        Mockito.doReturn("Alice").when(sqlRowSet1).getString("name");

        LoginRequest req = new LoginRequest();
        req.setEmail("alice@gmail.com");
        req.setPassword("alicepwd");

        LoginResponse res = new LoginResponse();
        res.setName("Alice");

        assertEquals(webApi.login(req), res);
    }

    @Test
    public void loginMappingEmailExistFail() {
        //CheckIfEmailExists
        SqlRowSet sqlRowSet = Mockito.mock(SqlRowSet.class);
        Mockito.doReturn(sqlRowSet).when(jdbcTemplate).queryForRowSet("SELECT * FROM users WHERE email = ?", "alice@gmail.com");
        Mockito.doReturn(true).when(sqlRowSet).isBeforeFirst();
        Mockito.doReturn(true).when(sqlRowSet).next();

        //AttemptLogin
        SqlRowSet sqlRowSet1 = Mockito.mock(SqlRowSet.class);
        Mockito.doReturn(sqlRowSet1).when(jdbcTemplate).queryForRowSet("SELECT * FROM users WHERE email = ? AND password = ?", "alice@gmail.com", "alicepwd");
        Mockito.doReturn(false).when(sqlRowSet1).next();

        LoginRequest req = new LoginRequest();
        req.setEmail("alice@gmail.com");
        req.setPassword("alicepwd");

        LoginResponse res = new LoginResponse();
        res.setName("error");

        assertEquals(webApi.login(req), res);
    }

    @Test
    public void loginMappingEmailDoesntExist() {
        //CheckIfEmailExists
        SqlRowSet sqlRowSet = Mockito.mock(SqlRowSet.class);
        Mockito.doReturn(sqlRowSet).when(jdbcTemplate).queryForRowSet("SELECT * FROM users WHERE email = ?", "alice@gmail.com");
        Mockito.doReturn(false).when(sqlRowSet).isBeforeFirst();

        LoginRequest req = new LoginRequest();
        req.setEmail("alice@gmail.com");
        req.setPassword("wrongpwd");

        LoginResponse res = new LoginResponse();
        res.setName("error");

        assertEquals(webApi.login(req), res);
    }
//    @Test
//    public void loginError(){
//        WebApi api = new WebApi();
//        LoginRequest loginReq = new LoginRequest();
//        loginReq.setEmail("error@gmail.com");
//        loginReq.setPassword("wrongpassword");
//        LoginResponse loginRes = new LoginResponse();
//        loginRes.setName("error");
//        assertEquals(api.login(loginReq).getName(),loginRes.getName());
//    }
//
//    @Test
//    public void loginSuccess(){
//        WebApi api = new WebApi();
//        LoginRequest loginReq = new LoginRequest();
//        loginReq.setEmail("alice@gmail.com");
//        loginReq.setPassword("alicepwd");
//        LoginResponse loginRes = new LoginResponse();
//        loginRes.setName("Alice");
//        assertEquals(api.login(loginReq).getName(),loginRes.getName());
//    }
}