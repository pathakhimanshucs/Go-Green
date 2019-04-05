package server;

import objects.*;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.configuration.injection.MockInjection;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.test.context.jdbc.Sql;


import javax.validation.constraints.AssertTrue;

import java.sql.Timestamp;
import java.util.LinkedList;

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
    public void loginMappingSuccessTest() {

        //AttemptLogin
        SqlRowSet sqlRowSet1 = Mockito.mock(SqlRowSet.class);
        Mockito.doReturn(sqlRowSet1).when(jdbcTemplate).queryForRowSet("SELECT * FROM users WHERE email = ?", "alice@gmail.com");
        Mockito.doReturn(true).when(sqlRowSet1).next();
        Mockito.doReturn(Encrypt.encryptPassWord("alice@gmail.com", "alicepwd")).when(sqlRowSet1).getString("password");
        Mockito.doReturn("Alice").when(sqlRowSet1).getString("name");
        Mockito.doReturn(true).when(sqlRowSet1).isBeforeFirst();

        String encryptedPWD = Encrypt.encryptPassWord("alice@gmail.com", "alicepwd");
        LoginRequest req = new LoginRequest();
        req.setEmail("alice@gmail.com");
        req.setPassword(encryptedPWD);

        LoginResponse res = new LoginResponse();
        res.setName("Alice");


        assertEquals(webApi.login(req), res);
    }

    @Test
    public void loginMappingWrongPassword() {

        //AttemptLogin
        SqlRowSet sqlRowSet1 = Mockito.mock(SqlRowSet.class);
        Mockito.doReturn(sqlRowSet1).when(jdbcTemplate).queryForRowSet("SELECT * FROM users WHERE email = ?", "alice@gmail.com");
        Mockito.doReturn(true).when(sqlRowSet1).next();
        Mockito.doReturn(Encrypt.encryptPassWord("alice@gmail.com", "alicepwd")).when(sqlRowSet1).getString("password");
        Mockito.doReturn("Alice").when(sqlRowSet1).getString("name");
        Mockito.doReturn(true).when(sqlRowSet1).isBeforeFirst();

        String encryptedPWD = Encrypt.encryptPassWord("alice@gmail.com", "wrongalicepwd");
        LoginRequest req = new LoginRequest();
        req.setEmail("alice@gmail.com");
        req.setPassword(encryptedPWD);

        LoginResponse res = new LoginResponse();
        res.setName("error");


        assertEquals(webApi.login(req), res);
    }

//    @Test
//    public void loginMappingWrongPasswordTest() {
//
//        //AttemptLogin
//        SqlRowSet sqlRowSet1 = Mockito.mock(SqlRowSet.class);
//        Mockito.doReturn(sqlRowSet1).when(jdbcTemplate).queryForRowSet("SELECT * FROM users WHERE email = ?", "alice@gmail.com");
//        Mockito.doReturn(false).when(sqlRowSet1).next();
//        String encryptedPWD = Encrypt.encryptPassWord("alice@gmail.com", "wrongalicepwd");
//        LoginRequest req = new LoginRequest();
//        req.setEmail("alice@gmail.com");
//        req.setPassword(encryptedPWD);
//
//        LoginResponse res = new LoginResponse();
//        res.setName("error");
//
//
//        assertEquals(webApi.login(req), res);
//    }

    @Test
    public void loginMappingEmailExistFail() {
        //CheckIfEmailExists
        SqlRowSet sqlRowSet = Mockito.mock(SqlRowSet.class);
//        Mockito.doReturn(sqlRowSet).when(jdbcTemplate).queryForRowSet("SELECT * FROM users WHERE email = ?", "alice@gmail.com");
//        Mockito.doReturn(true).when(sqlRowSet).isBeforeFirst();
//        Mockito.doReturn(true).when(sqlRowSet).next();

        //AttemptLogin
        SqlRowSet sqlRowSet1 = Mockito.mock(SqlRowSet.class);
        Mockito.doReturn(sqlRowSet1).when(jdbcTemplate).queryForRowSet("SELECT * FROM users WHERE email = ?", "alice@gmail.com");
//        Mockito.doReturn(false).when(sqlRowSet1).next();

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

    @Test
    public void registerMappingSuccess(){
        //CheckIfEmailExists
        SqlRowSet sqlRowSet = Mockito.mock(SqlRowSet.class);
        Mockito.doReturn(sqlRowSet).when(jdbcTemplate).queryForRowSet("SELECT * FROM users WHERE email = ?", "alice@gmail.com");
        Mockito.doReturn(true).when(sqlRowSet).isBeforeFirst();

        Mockito.doReturn(1).when(jdbcTemplate).update("INSERT INTO users (email, name, password) VALUES (?,?,?)", "alice@gmail.com", "Alice", "alicepwd");

        RegisterRequest req = new RegisterRequest();
        req.setEmail("alice@gmail.com");
        req.setName("Alice");
        req.setPassword("alicepwd");

        RegisterResponse res = new RegisterResponse();
        res.setName("Alice");
        res.setRegisterSuccess(true);

        Assert.assertEquals(webApi.register(req), res);
    }

    @Test
    public void registerMappingEmailExists(){
        //CheckIfEmailExists
        SqlRowSet sqlRowSet = Mockito.mock(SqlRowSet.class);
        Mockito.doReturn(sqlRowSet).when(jdbcTemplate).queryForRowSet("SELECT * FROM users WHERE email = ?", "alice@gmail.com");
        Mockito.doReturn(true).when(sqlRowSet).isBeforeFirst();
        Mockito.doReturn(true).when(sqlRowSet).next();

        RegisterRequest req = new RegisterRequest();
        req.setEmail("alice@gmail.com");
        req.setName("Alice");
        req.setPassword("alicepwd");

        RegisterResponse res = new RegisterResponse();
        res.setRegisterSuccess(false);

        Assert.assertEquals(webApi.register(req), res);
    }

    @Test
    public void addActivitySuccess(){
        //CheckTokenValidity Success
        SqlRowSet checkTokenValidityRowSet = Mockito.mock(SqlRowSet.class);
        String query = "SELECT * FROM sessiontokens WHERE token = ?";
        Mockito.doReturn(checkTokenValidityRowSet).when(jdbcTemplate).queryForRowSet(query, "token");
        Mockito.doReturn(true).when(checkTokenValidityRowSet).isBeforeFirst();
        Mockito.doReturn(true).when(checkTokenValidityRowSet).next();

//        //AddActivityInDB Success
//        SqlRowSet addActivityInDBRowSet = Mockito.mock(SqlRowSet.class);
//        String query2 = "INSERT INTO \"VEGMEAL\" (userid, time, amount) VALUES (?,?,?)";
//        Mockito.doReturn(addActivityInDBRowSet).when(jdbcTemplate).queryForRowSet(query2, 1, "2019-01-01 00:00:00", 1);

        //CheckIfEmailExists
        SqlRowSet sqlRowSet = Mockito.mock(SqlRowSet.class);
        Mockito.doReturn(sqlRowSet).when(jdbcTemplate).queryForRowSet("SELECT * FROM users WHERE email = ?", "alice@gmail.com");
        Mockito.doReturn(true).when(sqlRowSet).isBeforeFirst();
        Mockito.doReturn(true).when(sqlRowSet).next();
        Mockito.doReturn("Alice").when(sqlRowSet).getString("name");

        //GetUserIDFromEmail
        SqlRowSet sqlRowSet1 = Mockito.mock(SqlRowSet.class);
        Mockito.doReturn(sqlRowSet1).when(jdbcTemplate).queryForRowSet("select * from users where email = ?", "alice@gmail.com");
        Mockito.doReturn(true).when(sqlRowSet1).next();
        Mockito.doReturn(1).when(sqlRowSet1).getInt("userid");

        //Model Response
        ActivityResponse res = new ActivityResponse();
        res.setAddActivitySuccess(true);

        //Test Request
        ActivityRequest req = new ActivityRequest();
        AuthToken token = new AuthToken();
        token.setEmail("alice@gmail.com");
        token.setToken("token");
        req.setToken(token);
        Activity act = new Activity();
        act.setAmount(1);
        act.setActivity(Activity.ActivityObject.VEGMEAL);
        req.setActivity(act);

        Assert.assertEquals(webApi.addActivity(req), res);
    }

    @Test
    public void addActivityUserNotFound(){
        //CheckTokenValidity Success
        SqlRowSet checkTokenValidityRowSet = Mockito.mock(SqlRowSet.class);
        String query = "SELECT * FROM sessiontokens WHERE token = ?";
        Mockito.doReturn(checkTokenValidityRowSet).when(jdbcTemplate).queryForRowSet(query, "token");
        Mockito.doReturn(true).when(checkTokenValidityRowSet).isBeforeFirst();
        Mockito.doReturn(true).when(checkTokenValidityRowSet).next();

        //CheckIfEmailExists
        SqlRowSet sqlRowSet = Mockito.mock(SqlRowSet.class);
        Mockito.doReturn(sqlRowSet).when(jdbcTemplate).queryForRowSet("SELECT * FROM users WHERE email = ?", "alice@gmail.com");
        Mockito.doReturn(false).when(sqlRowSet).isBeforeFirst();

        //Model Response
        ActivityResponse res = new ActivityResponse();
        res.setAddActivitySuccess(false);

        //Test Request
        ActivityRequest req = new ActivityRequest();
        AuthToken token = new AuthToken();
        token.setEmail("alice@gmail.com");
        token.setToken("token");
        req.setToken(token);
        Activity act = new Activity();
        act.setAmount(1);
        act.setActivity(Activity.ActivityObject.VEGMEAL);
        req.setActivity(act);

        Assert.assertEquals(webApi.addActivity(req), res);
    }

    @Test
    public void addActivityAmount0(){
        //CheckTokenValidity Success
        SqlRowSet checkTokenValidityRowSet = Mockito.mock(SqlRowSet.class);
        String query = "SELECT * FROM sessiontokens WHERE token = ?";
        Mockito.doReturn(checkTokenValidityRowSet).when(jdbcTemplate).queryForRowSet(query, "token");
        Mockito.doReturn(true).when(checkTokenValidityRowSet).isBeforeFirst();
        Mockito.doReturn(true).when(checkTokenValidityRowSet).next();

        //CheckIfEmailExists
        SqlRowSet sqlRowSet = Mockito.mock(SqlRowSet.class);
        Mockito.doReturn(sqlRowSet).when(jdbcTemplate).queryForRowSet("SELECT * FROM users WHERE email = ?", "alice@gmail.com");
        Mockito.doReturn(true).when(sqlRowSet).isBeforeFirst();
        Mockito.doReturn(true).when(sqlRowSet).next();
        Mockito.doReturn("Alice").when(sqlRowSet).getString("name");

        //GetUserIDFromEmail
        SqlRowSet sqlRowSet1 = Mockito.mock(SqlRowSet.class);
        Mockito.doReturn(sqlRowSet1).when(jdbcTemplate).queryForRowSet("select * from users where email = ?", "alice@gmail.com");
        Mockito.doReturn(true).when(sqlRowSet1).next();
        Mockito.doReturn(1).when(sqlRowSet1).getInt("userid");

        //Model Response
        ActivityResponse res = new ActivityResponse();
        res.setAddActivitySuccess(false);

        //Test Request
        ActivityRequest req = new ActivityRequest();
        AuthToken token = new AuthToken();
        token.setEmail("alice@gmail.com");
        token.setToken("token");
        req.setToken(token);
        Activity act = new Activity();
        act.setAmount(0);
        act.setActivity(Activity.ActivityObject.VEGMEAL);
        req.setActivity(act);

        Assert.assertEquals(webApi.addActivity(req), res);
    }

    @Test
    public void addActivityInvalidToken(){
        //CheckTokenValidity Success
        SqlRowSet checkTokenValidityRowSet = Mockito.mock(SqlRowSet.class);
        String query = "SELECT * FROM sessiontokens WHERE token = ?";
        Mockito.doReturn(checkTokenValidityRowSet).when(jdbcTemplate).queryForRowSet(query, "token");
        Mockito.doReturn(false).when(checkTokenValidityRowSet).isBeforeFirst();

        //Model Response
        ActivityResponse res = new ActivityResponse();
        res.setAddActivitySuccess(false);

        //Test Request
        ActivityRequest req = new ActivityRequest();
        AuthToken token = new AuthToken();
        token.setEmail("alice@gmail.com");
        token.setToken("token");
        req.setToken(token);
        Activity act = new Activity();
        act.setAmount(0);
        act.setActivity(Activity.ActivityObject.VEGMEAL);
        req.setActivity(act);

        Assert.assertEquals(webApi.addActivity(req), res);
    }

    @Test
    public void getFriendsListSuccess(){
        //CheckTokenValidity Success
        SqlRowSet checkTokenValidityRowSet = Mockito.mock(SqlRowSet.class);
        String query = "SELECT * FROM sessiontokens WHERE token = ?";
        Mockito.doReturn(checkTokenValidityRowSet).when(jdbcTemplate).queryForRowSet(query, "token");
        Mockito.doReturn(true).when(checkTokenValidityRowSet).isBeforeFirst();
        Mockito.doReturn(true).when(checkTokenValidityRowSet).next();

        //CheckIfEmailExists
        SqlRowSet sqlRowSet = Mockito.mock(SqlRowSet.class);
        Mockito.doReturn(sqlRowSet).when(jdbcTemplate).queryForRowSet("SELECT * FROM users WHERE email = ?", "alice@gmail.com");
        Mockito.doReturn(true).when(sqlRowSet).isBeforeFirst();
        Mockito.doReturn(true).when(sqlRowSet).next();
        Mockito.doReturn("Alice").when(sqlRowSet).getString("name");

        //GetUserIDFromEmail
        SqlRowSet sqlRowSet1 = Mockito.mock(SqlRowSet.class);
        Mockito.doReturn(sqlRowSet1).when(jdbcTemplate).queryForRowSet("select * from users where email = ?", "alice@gmail.com");
        Mockito.doReturn(true).when(sqlRowSet1).next();
        Mockito.doReturn(1).when(sqlRowSet1).getInt("userid");

        //CheckIfEmailExists
        SqlRowSet sqlRowSet4 = Mockito.mock(SqlRowSet.class);
        Mockito.doReturn(sqlRowSet4).when(jdbcTemplate).queryForRowSet("SELECT * FROM users WHERE email = ?", "bob@gmail.com");
        Mockito.doReturn(true).when(sqlRowSet4).isBeforeFirst();
        Mockito.doReturn(true).when(sqlRowSet4).next();
        Mockito.doReturn("Bob").when(sqlRowSet4).getString("name");

        //GetUserIDFromEmail
        SqlRowSet sqlRowSet5 = Mockito.mock(SqlRowSet.class);
        Mockito.doReturn(sqlRowSet5).when(jdbcTemplate).queryForRowSet("select * from users where email = ?", "bob@gmail.com");
        Mockito.doReturn(true).when(sqlRowSet5).next();
        Mockito.doReturn(2).when(sqlRowSet5).getInt("userid");

        //GetAllFriendsFromDB
        SqlRowSet sqlRowSet2 = Mockito.mock(SqlRowSet.class);
        Mockito.doReturn(sqlRowSet2).when(jdbcTemplate).queryForRowSet("SELECT * FROM friends WHERE friend1 = ?", 1);
        Mockito.doReturn(true).when(sqlRowSet2).next();
        Mockito.doReturn(2).when(sqlRowSet2).getInt("friend2");

        //GetEmailFromUserID
        SqlRowSet sqlRowSet3 = Mockito.mock(SqlRowSet.class);
        Mockito.doReturn(sqlRowSet3).when(jdbcTemplate).queryForRowSet("select * from users where userid = ?", 2);
        Mockito.doReturn(true).when(sqlRowSet3).next();
        Mockito.doReturn("bob@gmail.com").when(sqlRowSet3).getString("email");

        //GetCO2FromDB
        SqlRowSet sqlRowSet6 = Mockito.mock(SqlRowSet.class);
        Mockito.doReturn(sqlRowSet6).when(jdbcTemplate).queryForRowSet("select * from activityvalues WHERE name = ?", "vegmeal");
        Mockito.doReturn(true).when(sqlRowSet6).next();
        Mockito.doReturn(0.0f).when(sqlRowSet6).getFloat("value");

        //GetTotalCo2ForUser
        SqlRowSet getActivitiesRow = Mockito.mock(SqlRowSet.class);
        Mockito.doReturn(getActivitiesRow).when(jdbcTemplate).queryForRowSet("SELECT * FROM activities WHERE userid = ?", 2);
        Mockito.doReturn(true).when(getActivitiesRow).next();
        Mockito.doReturn(1).when(getActivitiesRow).getInt("amount");
        Mockito.doReturn(new Timestamp(1)).when(getActivitiesRow).getTimestamp("time");
        Mockito.doReturn("VEGMEAL").when(getActivitiesRow).getString("table_name");

        //Model Response
        FriendListResponse res = new FriendListResponse();
        res.setFriendsListSuccess(true);
        LinkedList<Friend> friendsList = new LinkedList<>();

        Friend temp = new Friend();
        temp.setEmail("bob@gmail.com");
        temp.setTotalCO2(0.0f);
        res.setFriends(friendsList);
        friendsList.add(temp);

        res.setEmail("alice@gmail.com");

        //Test request
        FriendsListRequest req = new FriendsListRequest();
        AuthToken token = new AuthToken();
        token.setToken("token");
        token.setEmail("alice@gmail.com");
        req.setToken(token);

        Assert.assertEquals(webApi.getFriendsList(req), res);
    }

    @Test
    public void getFriendsListFail(){
        //CheckTokenValidity Success
        SqlRowSet checkTokenValidityRowSet = Mockito.mock(SqlRowSet.class);
        String query = "SELECT * FROM sessiontokens WHERE token = ?";
        Mockito.doReturn(checkTokenValidityRowSet).when(jdbcTemplate).queryForRowSet(query, "token");
        Mockito.doReturn(true).when(checkTokenValidityRowSet).isBeforeFirst();
        Mockito.doReturn(true).when(checkTokenValidityRowSet).next();

        //CheckIfEmailExists
        SqlRowSet sqlRowSet = Mockito.mock(SqlRowSet.class);
        Mockito.doReturn(sqlRowSet).when(jdbcTemplate).queryForRowSet("SELECT * FROM users WHERE email = ?", "alice@gmail.com");
        Mockito.doReturn(true).when(sqlRowSet).isBeforeFirst();
        Mockito.doReturn(true).when(sqlRowSet).next();
        Mockito.doReturn("Alice").when(sqlRowSet).getString("name");

        //GetUserIDFromEmail
        SqlRowSet sqlRowSet1 = Mockito.mock(SqlRowSet.class);
        Mockito.doReturn(sqlRowSet1).when(jdbcTemplate).queryForRowSet("select * from users where email = ?", "alice@gmail.com");
        Mockito.doReturn(true).when(sqlRowSet1).next();
        Mockito.doReturn(-1).when(sqlRowSet1).getInt("userid");

        //Model Response
        FriendListResponse res = new FriendListResponse();
        res.setFriendsListSuccess(false);
        res.setEmail("alice@gmail.com");

        //Test request
        FriendsListRequest req = new FriendsListRequest();
        AuthToken token = new AuthToken();
        token.setToken("token");
        token.setEmail("alice@gmail.com");
        req.setToken(token);

        Assert.assertEquals(webApi.getFriendsList(req), res);
    }

    @Test
    public void getFriendsListWrongToken(){
        //CheckTokenValidity Success
        SqlRowSet checkTokenValidityRowSet = Mockito.mock(SqlRowSet.class);
        String query = "SELECT * FROM sessiontokens WHERE token = ?";
        Mockito.doReturn(checkTokenValidityRowSet).when(jdbcTemplate).queryForRowSet(query, "wrongtoken");
        Mockito.doReturn(false).when(checkTokenValidityRowSet).isBeforeFirst();

        //Model Response
        FriendListResponse res = new FriendListResponse();
        res.setEmail("alice@gmail.com");
        res.setFriendsListSuccess(false);

        //Test request
        FriendsListRequest req = new FriendsListRequest();
        AuthToken token = new AuthToken();
        token.setToken("wrongtoken");
        token.setEmail("alice@gmail.com");
        req.setToken(token);

        Assert.assertEquals(webApi.getFriendsList(req), res);
    }

    @Test
    public void getActivityListSuccess(){
        //CheckTokenValidity Success
        SqlRowSet checkTokenValidityRowSet = Mockito.mock(SqlRowSet.class);
        String query = "SELECT * FROM sessiontokens WHERE token = ?";
        Mockito.doReturn(checkTokenValidityRowSet).when(jdbcTemplate).queryForRowSet(query, "token");
        Mockito.doReturn(true).when(checkTokenValidityRowSet).isBeforeFirst();
        Mockito.doReturn(true).when(checkTokenValidityRowSet).next();

        //CheckIfEmailExists
        SqlRowSet sqlRowSet = Mockito.mock(SqlRowSet.class);
        Mockito.doReturn(sqlRowSet).when(jdbcTemplate).queryForRowSet("SELECT * FROM users WHERE email = ?", "alice@gmail.com");
        Mockito.doReturn(true).when(sqlRowSet).isBeforeFirst();
        Mockito.doReturn(true).when(sqlRowSet).next();
        Mockito.doReturn("Alice").when(sqlRowSet).getString("name");

        //GetUserIDFromEmail
        SqlRowSet sqlRowSet1 = Mockito.mock(SqlRowSet.class);
        Mockito.doReturn(sqlRowSet1).when(jdbcTemplate).queryForRowSet("select * from users where email = ?", "alice@gmail.com");
        Mockito.doReturn(true).when(sqlRowSet1).next();
        Mockito.doReturn(1).when(sqlRowSet1).getInt("userid");

        //GetAllActivities
        SqlRowSet sqlRowSet2 = Mockito.mock(SqlRowSet.class);
        Mockito.doReturn(sqlRowSet2).when(jdbcTemplate).queryForRowSet("SELECT * FROM activities WHERE userid = ?", 1);
        Mockito.doReturn(true).when(sqlRowSet2).next();
        Mockito.doReturn(1).when(sqlRowSet2).getInt("amount");
        Mockito.doReturn(new Timestamp(1)).when(sqlRowSet2).getTimestamp("time");
        Mockito.doReturn("VEGMEAL").when(sqlRowSet2).getString("table_name");

        //CalculateCO2
        SqlRowSet sqlRowSet3 = Mockito.mock(SqlRowSet.class);
        Mockito.doReturn(sqlRowSet3).when(jdbcTemplate).queryForRowSet("select * from activityvalues WHERE name = ?", "vegmeal");
        Mockito.doReturn(true).when(sqlRowSet3).next();
        Mockito.doReturn(14.5f).when(sqlRowSet3).getFloat("value");

        //Model Response
        ActivityListResponse res = new ActivityListResponse();
        res.setActivityListSuccess(true);
        res.setEmail("alice@gmail.com");
        Activity activity = new Activity();
        activity.setAmount(1);
        activity.setTime(new Timestamp(1));
        activity.setActivity(Activity.ActivityObject.VEGMEAL);
        activity.setCo2Amount(14.5f);
        LinkedList<Activity> activities = new LinkedList<>();
        activities.add(activity);
        res.setActivities(activities);

        //Test request
        ActivityListRequest req = new ActivityListRequest();
        AuthToken token = new AuthToken();
        token.setToken("token");
        token.setEmail("alice@gmail.com");
        req.setToken(token);

        Assert.assertEquals(webApi.getActivityList(req), res);
    }

    @Test
    public void getActivityListWrongToken(){
        //CheckTokenValidity Success
        SqlRowSet checkTokenValidityRowSet = Mockito.mock(SqlRowSet.class);
        String query = "SELECT * FROM sessiontokens WHERE token = ?";
        Mockito.doReturn(checkTokenValidityRowSet).when(jdbcTemplate).queryForRowSet(query, "wrongtoken");
        Mockito.doReturn(false).when(checkTokenValidityRowSet).isBeforeFirst();

        //Model Response
        ActivityListResponse res = new ActivityListResponse();
        res.setEmail("alice@gmail.com");
        res.setActivityListSuccess(false);

        //Test request
        ActivityListRequest req = new ActivityListRequest();
        AuthToken token = new AuthToken();
        token.setToken("wrongtoken");
        token.setEmail("alice@gmail.com");
        req.setToken(token);

        Assert.assertEquals(webApi.getActivityList(req), res);
    }

    @Test
    public void getActivityListFail(){
        //CheckTokenValidity Success
        SqlRowSet checkTokenValidityRowSet = Mockito.mock(SqlRowSet.class);
        String query = "SELECT * FROM sessiontokens WHERE token = ?";
        Mockito.doReturn(checkTokenValidityRowSet).when(jdbcTemplate).queryForRowSet(query, "token");
        Mockito.doReturn(true).when(checkTokenValidityRowSet).isBeforeFirst();
        Mockito.doReturn(true).when(checkTokenValidityRowSet).next();

        //CheckIfEmailExists
        SqlRowSet sqlRowSet = Mockito.mock(SqlRowSet.class);
        Mockito.doReturn(sqlRowSet).when(jdbcTemplate).queryForRowSet("SELECT * FROM users WHERE email = ?", "alice@gmail.com");
        Mockito.doReturn(true).when(sqlRowSet).isBeforeFirst();
        Mockito.doReturn(true).when(sqlRowSet).next();
        Mockito.doReturn("Alice").when(sqlRowSet).getString("name");

        //GetUserIDFromEmail
        SqlRowSet sqlRowSet1 = Mockito.mock(SqlRowSet.class);
        Mockito.doReturn(sqlRowSet1).when(jdbcTemplate).queryForRowSet("select * from users where email = ?", "alice@gmail.com");
        Mockito.doReturn(true).when(sqlRowSet1).next();
        Mockito.doReturn(-1).when(sqlRowSet1).getInt("userid");

        //Model Response
        ActivityListResponse res = new ActivityListResponse();
        res.setEmail("alice@gmail.com");
        res.setActivityListSuccess(false);

        //Test request
        ActivityListRequest req = new ActivityListRequest();
        AuthToken token = new AuthToken();
        token.setToken("token");
        token.setEmail("alice@gmail.com");
        req.setToken(token);

        Assert.assertEquals(webApi.getActivityList(req), res);
    }

    @Test
    public void addFriendSuccess(){
        //CheckTokenValidity Success
        SqlRowSet checkTokenValidityRowSet = Mockito.mock(SqlRowSet.class);
        String query = "SELECT * FROM sessiontokens WHERE token = ?";
        Mockito.doReturn(checkTokenValidityRowSet).when(jdbcTemplate).queryForRowSet(query, "token");
        Mockito.doReturn(true).when(checkTokenValidityRowSet).isBeforeFirst();
        Mockito.doReturn(true).when(checkTokenValidityRowSet).next();

        //CheckIfEmailExists
        SqlRowSet sqlRowSet = Mockito.mock(SqlRowSet.class);
        Mockito.doReturn(sqlRowSet).when(jdbcTemplate).queryForRowSet("SELECT * FROM users WHERE email = ?", "alice@gmail.com");
        Mockito.doReturn(true).when(sqlRowSet).isBeforeFirst();
        Mockito.doReturn(true).when(sqlRowSet).next();
        Mockito.doReturn("Alice").when(sqlRowSet).getString("name");

        //CheckIfEmailExists
        SqlRowSet sqlRowSet3 = Mockito.mock(SqlRowSet.class);
        Mockito.doReturn(sqlRowSet3).when(jdbcTemplate).queryForRowSet("SELECT * FROM users WHERE email = ?", "bob@gmail.com");
        Mockito.doReturn(true).when(sqlRowSet3).isBeforeFirst();
        Mockito.doReturn(true).when(sqlRowSet3).next();
        Mockito.doReturn("Bob").when(sqlRowSet3).getString("name");

        //GetUserIDFromEmail
        SqlRowSet sqlRowSet1 = Mockito.mock(SqlRowSet.class);
        Mockito.doReturn(sqlRowSet1).when(jdbcTemplate).queryForRowSet("select * from users where email = ?", "alice@gmail.com");
        Mockito.doReturn(true).when(sqlRowSet1).next();
        Mockito.doReturn(1).when(sqlRowSet1).getInt("userid");

        //GetUserIDFromEmail
        SqlRowSet sqlRowSet2 = Mockito.mock(SqlRowSet.class);
        Mockito.doReturn(sqlRowSet2).when(jdbcTemplate).queryForRowSet("select * from users where email = ?", "bob@gmail.com");
        Mockito.doReturn(true).when(sqlRowSet2).next();
        Mockito.doReturn(2).when(sqlRowSet2).getInt("userid");

        Mockito.doReturn(1).when(jdbcTemplate).update("INSERT INTO friends (friend1, friend2) VALUES (?,?)", 1,2);

        //Model Response
        AddFriendResponse res = new AddFriendResponse();
        res.setAddFriendSuccess(true);
        res.setFriend2("bob@gmail.com");

        //Test Request
        AddFriendRequest req = new AddFriendRequest();
        AuthToken token = new AuthToken();
        token.setToken("token");
        token.setEmail("alice@gmail.com");
        req.setToken(token);
        req.setFriend2email("bob@gmail.com");

        Assert.assertEquals(webApi.addFriend(req), res);

    }

    @Test
    public void addFriendUser2DoesntExist(){
        //CheckTokenValidity Success
        SqlRowSet checkTokenValidityRowSet = Mockito.mock(SqlRowSet.class);
        String query = "SELECT * FROM sessiontokens WHERE token = ?";
        Mockito.doReturn(checkTokenValidityRowSet).when(jdbcTemplate).queryForRowSet(query, "token");
        Mockito.doReturn(true).when(checkTokenValidityRowSet).isBeforeFirst();
        Mockito.doReturn(true).when(checkTokenValidityRowSet).next();

        //CheckIfEmailExists
        SqlRowSet sqlRowSet = Mockito.mock(SqlRowSet.class);
        Mockito.doReturn(sqlRowSet).when(jdbcTemplate).queryForRowSet("SELECT * FROM users WHERE email = ?", "alice@gmail.com");
        Mockito.doReturn(true).when(sqlRowSet).isBeforeFirst();
        Mockito.doReturn(true).when(sqlRowSet).next();
        Mockito.doReturn("Alice").when(sqlRowSet).getString("name");

        //CheckIfEmailExists
        SqlRowSet sqlRowSet3 = Mockito.mock(SqlRowSet.class);
        Mockito.doReturn(sqlRowSet3).when(jdbcTemplate).queryForRowSet("SELECT * FROM users WHERE email = ?", "bob@gmail.com");
        Mockito.doReturn(true).when(sqlRowSet3).isBeforeFirst();
        Mockito.doReturn(true).when(sqlRowSet3).next();
        Mockito.doReturn("Bob").when(sqlRowSet3).getString("name");

        //GetUserIDFromEmail
        SqlRowSet sqlRowSet1 = Mockito.mock(SqlRowSet.class);
        Mockito.doReturn(sqlRowSet1).when(jdbcTemplate).queryForRowSet("select * from users where email = ?", "alice@gmail.com");
        Mockito.doReturn(true).when(sqlRowSet1).next();
        Mockito.doReturn(-1).when(sqlRowSet1).getInt("userid");

        //GetUserIDFromEmail
        SqlRowSet sqlRowSet2 = Mockito.mock(SqlRowSet.class);
        Mockito.doReturn(sqlRowSet2).when(jdbcTemplate).queryForRowSet("select * from users where email = ?", "bob@gmail.com");
        Mockito.doReturn(true).when(sqlRowSet2).next();
        Mockito.doReturn(2).when(sqlRowSet2).getInt("userid");

//        Mockito.doReturn(1).when(jdbcTemplate).update("INSERT INTO friends (friend1, friend2) VALUES (?,?)", 1,2);

        //Model Response
        AddFriendResponse res = new AddFriendResponse();
        res.setAddFriendSuccess(false);

        //Test Request
        AddFriendRequest req = new AddFriendRequest();
        AuthToken token = new AuthToken();
        token.setToken("token");
        token.setEmail("alice@gmail.com");
        req.setToken(token);
        req.setFriend2email("bob@gmail.com");

        Assert.assertEquals(webApi.addFriend(req), res);

    }

    @Test
    public void addFriendUser1DoesntExist(){
        //CheckTokenValidity Success
        SqlRowSet checkTokenValidityRowSet = Mockito.mock(SqlRowSet.class);
        String query = "SELECT * FROM sessiontokens WHERE token = ?";
        Mockito.doReturn(checkTokenValidityRowSet).when(jdbcTemplate).queryForRowSet(query, "token");
        Mockito.doReturn(true).when(checkTokenValidityRowSet).isBeforeFirst();
        Mockito.doReturn(true).when(checkTokenValidityRowSet).next();

        //CheckIfEmailExists
        SqlRowSet sqlRowSet = Mockito.mock(SqlRowSet.class);
        Mockito.doReturn(sqlRowSet).when(jdbcTemplate).queryForRowSet("SELECT * FROM users WHERE email = ?", "alice@gmail.com");
        Mockito.doReturn(true).when(sqlRowSet).isBeforeFirst();
        Mockito.doReturn(true).when(sqlRowSet).next();
        Mockito.doReturn("Alice").when(sqlRowSet).getString("name");

        //CheckIfEmailExists
        SqlRowSet sqlRowSet3 = Mockito.mock(SqlRowSet.class);
        Mockito.doReturn(sqlRowSet3).when(jdbcTemplate).queryForRowSet("SELECT * FROM users WHERE email = ?", "bob@gmail.com");
        Mockito.doReturn(true).when(sqlRowSet3).isBeforeFirst();
        Mockito.doReturn(true).when(sqlRowSet3).next();
        Mockito.doReturn("Bob").when(sqlRowSet3).getString("name");

        //GetUserIDFromEmail
        SqlRowSet sqlRowSet1 = Mockito.mock(SqlRowSet.class);
        Mockito.doReturn(sqlRowSet1).when(jdbcTemplate).queryForRowSet("select * from users where email = ?", "alice@gmail.com");
        Mockito.doReturn(true).when(sqlRowSet1).next();
        Mockito.doReturn(1).when(sqlRowSet1).getInt("userid");

        //GetUserIDFromEmail
        SqlRowSet sqlRowSet2 = Mockito.mock(SqlRowSet.class);
        Mockito.doReturn(sqlRowSet2).when(jdbcTemplate).queryForRowSet("select * from users where email = ?", "bob@gmail.com");
        Mockito.doReturn(true).when(sqlRowSet2).next();
        Mockito.doReturn(-1).when(sqlRowSet2).getInt("userid");

//        Mockito.doReturn(1).when(jdbcTemplate).update("INSERT INTO friends (friend1, friend2) VALUES (?,?)", 1,2);

        //Model Response
        AddFriendResponse res = new AddFriendResponse();
        res.setAddFriendSuccess(false);

        //Test Request
        AddFriendRequest req = new AddFriendRequest();
        AuthToken token = new AuthToken();
        token.setToken("token");
        token.setEmail("alice@gmail.com");
        req.setToken(token);
        req.setFriend2email("bob@gmail.com");

        Assert.assertEquals(webApi.addFriend(req), res);

    }

    @Test
    public void addFriendWrongToken(){
        //CheckTokenValidity Success
        SqlRowSet checkTokenValidityRowSet = Mockito.mock(SqlRowSet.class);
        String query = "SELECT * FROM sessiontokens WHERE token = ?";
        Mockito.doReturn(checkTokenValidityRowSet).when(jdbcTemplate).queryForRowSet(query, "wrongtoken");
        Mockito.doReturn(false).when(checkTokenValidityRowSet).isBeforeFirst();

        //Model Response
        AddFriendResponse res = new AddFriendResponse();
        res.setAddFriendSuccess(false);

        //Test request
        AddFriendRequest req = new AddFriendRequest();
        AuthToken token = new AuthToken();
        token.setToken("wrongtoken");
        token.setEmail("alice@gmail.com");
        req.setToken(token);
        req.setFriend2email("bob@gmail.com");

        Assert.assertEquals(webApi.addFriend(req), res);
    }

    @Test
    public void addFriendAlreadyFriends(){
        //CheckTokenValidity Success
        SqlRowSet checkTokenValidityRowSet = Mockito.mock(SqlRowSet.class);
        String query = "SELECT * FROM sessiontokens WHERE token = ?";
        Mockito.doReturn(checkTokenValidityRowSet).when(jdbcTemplate).queryForRowSet(query, "token");
        Mockito.doReturn(true).when(checkTokenValidityRowSet).isBeforeFirst();
        Mockito.doReturn(true).when(checkTokenValidityRowSet).next();

        //CheckIfEmailExists
        SqlRowSet sqlRowSet = Mockito.mock(SqlRowSet.class);
        Mockito.doReturn(sqlRowSet).when(jdbcTemplate).queryForRowSet("SELECT * FROM users WHERE email = ?", "alice@gmail.com");
        Mockito.doReturn(true).when(sqlRowSet).isBeforeFirst();
        Mockito.doReturn(true).when(sqlRowSet).next();
        Mockito.doReturn("Alice").when(sqlRowSet).getString("name");

        //CheckIfEmailExists
        SqlRowSet sqlRowSet3 = Mockito.mock(SqlRowSet.class);
        Mockito.doReturn(sqlRowSet3).when(jdbcTemplate).queryForRowSet("SELECT * FROM users WHERE email = ?", "bob@gmail.com");
        Mockito.doReturn(true).when(sqlRowSet3).isBeforeFirst();
        Mockito.doReturn(true).when(sqlRowSet3).next();
        Mockito.doReturn("Bob").when(sqlRowSet3).getString("name");

        //GetUserIDFromEmail
        SqlRowSet sqlRowSet1 = Mockito.mock(SqlRowSet.class);
        Mockito.doReturn(sqlRowSet1).when(jdbcTemplate).queryForRowSet("select * from users where email = ?", "alice@gmail.com");
        Mockito.doReturn(true).when(sqlRowSet1).next();
        Mockito.doReturn(1).when(sqlRowSet1).getInt("userid");

        //GetUserIDFromEmail
        SqlRowSet sqlRowSet2 = Mockito.mock(SqlRowSet.class);
        Mockito.doReturn(sqlRowSet2).when(jdbcTemplate).queryForRowSet("select * from users where email = ?", "bob@gmail.com");
        Mockito.doReturn(true).when(sqlRowSet2).next();
        Mockito.doReturn(2).when(sqlRowSet2).getInt("userid");

        DataAccessException exception = Mockito.mock(DataAccessException.class);
        Mockito.doThrow(exception).when(jdbcTemplate).update("INSERT INTO friends (friend1, friend2) VALUES (?,?)", 1,2);

        //Model Response
        AddFriendResponse res = new AddFriendResponse();
        res.setAddFriendSuccess(false);

        //Test Request
        AddFriendRequest req = new AddFriendRequest();
        AuthToken token = new AuthToken();
        token.setToken("token");
        token.setEmail("alice@gmail.com");
        req.setToken(token);
        req.setFriend2email("bob@gmail.com");

        Assert.assertEquals(webApi.addFriend(req), res);
    }

    @Test
    public void attemptLoginFailTest(){
        SqlRowSet mock = Mockito.mock(SqlRowSet.class);
        Mockito.doReturn(mock).when(jdbcTemplate).queryForRowSet("SELECT * FROM users WHERE email = ?", "alice@gmail.com");
        Mockito.doReturn(false).when(mock).next();

        Assert.assertNull(webApi.attemptLogin("alice@gmail.com", "alice"));
    }

    @Test
    public void getCO2FromDBFail(){
        SqlRowSet mock = Mockito.mock(SqlRowSet.class);
        Mockito.doReturn(mock).when(jdbcTemplate).queryForRowSet("select * from activityvalues WHERE name = ?", "WRONGTABLE");
        Mockito.doReturn(false).when(mock).next();

        assertEquals(webApi.getCO2FromDB("WRONGTABLE"), 0, 0);
    }

    @Test
    public void getUserIdFromEmailFail(){
        SqlRowSet mock = Mockito.mock(SqlRowSet.class);
        Mockito.doReturn(mock).when(jdbcTemplate).queryForRowSet("select * from users where email = ?", "alice@gmail.com");
        Mockito.doReturn(false).when(mock).next();

        //CheckIfEmailExists
        SqlRowSet sqlRowSet = Mockito.mock(SqlRowSet.class);
        Mockito.doReturn(sqlRowSet).when(jdbcTemplate).queryForRowSet("SELECT * FROM users WHERE email = ?", "alice@gmail.com");
        Mockito.doReturn(true).when(sqlRowSet).isBeforeFirst();
        Mockito.doReturn(true).when(sqlRowSet).next();
        Mockito.doReturn("Alice").when(sqlRowSet).getString("name");

        Assert.assertEquals(webApi.getUserIdFromEmail("alice@gmail.com"), -1);
    }

    @Test
    public void getEmailFromIDFail(){
        SqlRowSet mock = Mockito.mock(SqlRowSet.class);
        Mockito.doReturn(mock).when(jdbcTemplate).queryForRowSet("select * from users where userid = ?", 1);
        Mockito.doReturn(false).when(mock).next();

        Assert.assertNull(webApi.getEmailFromUserID(1));
    }

    @Test
    public void checkTokenValidityFail(){
        Mockito.doReturn(null).when(jdbcTemplate).queryForRowSet("SELECT remove_expired_tokens()");

        SqlRowSet mockRowSet = Mockito.mock(SqlRowSet.class);
        Mockito.doReturn(mockRowSet).when(jdbcTemplate).queryForRowSet("SELECT * FROM sessiontokens WHERE token = ?", "token");
        Mockito.doReturn(true).when(mockRowSet).isBeforeFirst();
        Mockito.doReturn(false).when(mockRowSet).next();

        Assert.assertFalse(webApi.checkTokenValidity("token"));
    }
}