package server;

import objects.*;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
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
    public void addVegMealSuccess(){
        //CheckIfEmailExists
        SqlRowSet sqlRowSet = Mockito.mock(SqlRowSet.class);
        Mockito.doReturn(sqlRowSet).when(jdbcTemplate).queryForRowSet("SELECT * FROM users WHERE email = ?", "alice@gmail.com");
        Mockito.doReturn(true).when(sqlRowSet).isBeforeFirst();
        Mockito.doReturn(true).when(sqlRowSet).next();

        //GetUserIDFromEmail
        SqlRowSet sqlRowSet1 = Mockito.mock(SqlRowSet.class);
        Mockito.doReturn(sqlRowSet1).when(jdbcTemplate).queryForRowSet("select * from users where email = ?", "alice@gmail.com");
        Mockito.doReturn(true).when(sqlRowSet1).next();
        Mockito.doReturn(1).when(sqlRowSet1).getInt("userid");

        //AddVMealInDB
        Mockito.doReturn(1).when(jdbcTemplate).update(Mockito.anyString(), Mockito.anyInt(), Mockito.any(Timestamp.class), Mockito.anyInt());

        VegetarianMealRequest req = new VegetarianMealRequest();
        req.setEmail("alice@gmail.com");
        req.setAmount(1);

        VegetarianMealResponse res = new VegetarianMealResponse();
        res.setAddVegetarianMealSuccess(true);

        Assert.assertEquals(webApi.addvegmeal(req), res);
    }

    @Test
    public void addVegMealEmailDoesntExist(){
        //CheckIfEmailExists
        SqlRowSet sqlRowSet = Mockito.mock(SqlRowSet.class);
        Mockito.doReturn(sqlRowSet).when(jdbcTemplate).queryForRowSet("SELECT * FROM users WHERE email = ?", "alice@gmail.com");
        Mockito.doReturn(false).when(sqlRowSet).isBeforeFirst();

        VegetarianMealRequest req = new VegetarianMealRequest();
        req.setEmail("alice@gmail.com");
        req.setAmount(1);

        VegetarianMealResponse res = new VegetarianMealResponse();
        res.setAddVegetarianMealSuccess(false);

        Assert.assertEquals(webApi.addvegmeal(req), res);
    }

    @Test
    public void addVegMeal0Amount(){
        //CheckIfEmailExists
        SqlRowSet sqlRowSet = Mockito.mock(SqlRowSet.class);
        Mockito.doReturn(sqlRowSet).when(jdbcTemplate).queryForRowSet("SELECT * FROM users WHERE email = ?", "alice@gmail.com");
        Mockito.doReturn(true).when(sqlRowSet).isBeforeFirst();
        Mockito.doReturn(true).when(sqlRowSet).next();

        //GetUserIDFromEmail
        SqlRowSet sqlRowSet1 = Mockito.mock(SqlRowSet.class);
        Mockito.doReturn(sqlRowSet1).when(jdbcTemplate).queryForRowSet("select * from users where email = ?", "alice@gmail.com");
        Mockito.doReturn(true).when(sqlRowSet1).next();
        Mockito.doReturn(1).when(sqlRowSet1).getInt("userid");

        VegetarianMealRequest req = new VegetarianMealRequest();
        req.setEmail("alice@gmail.com");
        req.setAmount(0);

        VegetarianMealResponse res = new VegetarianMealResponse();
        res.setAddVegetarianMealSuccess(false);

        Assert.assertEquals(webApi.addvegmeal(req), res);
    }

    @Test
    public void getVegMealListSuccess(){
        //CheckIfEmailExists
        SqlRowSet sqlRowSet = Mockito.mock(SqlRowSet.class);
        Mockito.doReturn(sqlRowSet).when(jdbcTemplate).queryForRowSet("SELECT * FROM users WHERE email = ?", "alice@gmail.com");
        Mockito.doReturn(true).when(sqlRowSet).isBeforeFirst();
        Mockito.doReturn(true).when(sqlRowSet).next();

        //GetUserIDFromEmail
        SqlRowSet sqlRowSet1 = Mockito.mock(SqlRowSet.class);
        Mockito.doReturn(sqlRowSet1).when(jdbcTemplate).queryForRowSet("select * from users where email = ?", "alice@gmail.com");
        Mockito.doReturn(true).when(sqlRowSet1).next();
        Mockito.doReturn(1).when(sqlRowSet1).getInt("userid");

        //GetAllMealsFromDB
        SqlRowSet sqlRowSet2 = Mockito.mock(SqlRowSet.class);
        Mockito.doReturn(sqlRowSet2).when(jdbcTemplate).queryForRowSet("SELECT * FROM VEGMEAL WHERE USERID = ?", 1);
        Mockito.doReturn(true, false).when(sqlRowSet2).next();
        Mockito.doReturn(1).when(sqlRowSet2).getInt("amount");
        Mockito.doReturn(new Timestamp(1)).when(sqlRowSet2).getTimestamp("time");

        LinkedList<Meal> resList = new LinkedList<Meal>();
        Meal tempMeal = new Meal();
        tempMeal.setTime(new Timestamp(1));
        tempMeal.setMealAmount(1);
        resList.add(tempMeal);

        VegetarianMealListResponse resp = new VegetarianMealListResponse();
        resp.setMealsListSuccess(true);
        resp.setMeals(resList);
        resp.setEmail("alice@gmail.com");

        VegetarianMealListRequest req = new VegetarianMealListRequest();
        req.setEmail("alice@gmail.com");

        Assert.assertEquals(webApi.getVegMealsList(req), resp);
    }

    @Test
    public void getVegMealListNull(){
        //CheckIfEmailExists
        SqlRowSet sqlRowSet = Mockito.mock(SqlRowSet.class);
        Mockito.doReturn(sqlRowSet).when(jdbcTemplate).queryForRowSet("SELECT * FROM users WHERE email = ?", "alice@gmail.com");
        Mockito.doReturn(true).when(sqlRowSet).isBeforeFirst();
        Mockito.doReturn(true).when(sqlRowSet).next();

        //GetUserIDFromEmail
        SqlRowSet sqlRowSet1 = Mockito.mock(SqlRowSet.class);
        Mockito.doReturn(sqlRowSet1).when(jdbcTemplate).queryForRowSet("select * from users where email = ?", "alice@gmail.com");
        Mockito.doReturn(true).when(sqlRowSet1).next();
        Mockito.doReturn(1).when(sqlRowSet1).getInt("userid");

        //GetAllMealsFromDB
        SqlRowSet sqlRowSet2 = Mockito.mock(SqlRowSet.class);
        Mockito.doReturn(sqlRowSet2).when(jdbcTemplate).queryForRowSet("SELECT * FROM VEGMEAL WHERE USERID = ?", 1);
        Mockito.doReturn(false).when(sqlRowSet2).next();

        LinkedList<Meal> resList = new LinkedList<>();

        VegetarianMealListResponse resp = new VegetarianMealListResponse();
        resp.setMealsListSuccess(true);
        resp.setMeals(resList);
        resp.setEmail("alice@gmail.com");

        VegetarianMealListRequest req = new VegetarianMealListRequest();
        req.setEmail("alice@gmail.com");

        Assert.assertEquals(webApi.getVegMealsList(req), resp);
    }

    @Test
    public void getVegMealListFail(){
        //CheckIfEmailExists
        SqlRowSet sqlRowSet = Mockito.mock(SqlRowSet.class);
        Mockito.doReturn(sqlRowSet).when(jdbcTemplate).queryForRowSet("SELECT * FROM users WHERE email = ?", "alice@gmail.com");
        Mockito.doReturn(false).when(sqlRowSet).isBeforeFirst();

        VegetarianMealListResponse resp = new VegetarianMealListResponse();
        resp.setMealsListSuccess(false);
        resp.setEmail("alice@gmail.com");

        VegetarianMealListRequest req = new VegetarianMealListRequest();
        req.setEmail("alice@gmail.com");

        Assert.assertEquals(webApi.getVegMealsList(req), resp);
    }

    @Test
    public void getUserEmailFail(){
        //CheckIfEmailExists
        SqlRowSet sqlRowSet = Mockito.mock(SqlRowSet.class);
        Mockito.doReturn(sqlRowSet).when(jdbcTemplate).queryForRowSet("SELECT * FROM users WHERE email = ?", "alice@gmail.com");
        Mockito.doReturn(true).when(sqlRowSet).isBeforeFirst();
        Mockito.doReturn(true).when(sqlRowSet).next();

        //GetUserIDFromEmail
        SqlRowSet sqlRowSet1 = Mockito.mock(SqlRowSet.class);
        Mockito.doReturn(sqlRowSet1).when(jdbcTemplate).queryForRowSet("select * from users where email = ?", "alice@gmail.com");
        Mockito.doReturn(false).when(sqlRowSet1).next();

        Assert.assertEquals(webApi.getUserIdFromEmail("alice@gmail.com"), -1);
    }
}