package server;

import objects.LoginRequest;
import objects.LoginResponse;
import objects.Meal;
import objects.RegisterRequest;
import objects.RegisterResponse;
import objects.VegetarianMealListRequest;
import objects.VegetarianMealListResponse;
import objects.VegetarianMealRequest;
import objects.VegetarianMealResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.LinkedList;

@RestController
public class WebApi {

    private static final Logger logger = LoggerFactory.getLogger(WebApi.class);

    @Autowired
    JdbcTemplate jdbcTemplate;

    @RequestMapping("/request")
    public String request(@RequestParam(value = "name", defaultValue = "Anonymous") String name) {
        System.out.println("Received a request");
        return "Hello " + name;
    }

    /**
     * Request mapping for login function.
     *
     * @param loginReq LoginRequest object that is sent from the client
     * @return Returns a json object with the user name if the email and password are correct.
     */
    @RequestMapping(path = "/login", consumes = "application/json", produces = "application/json")
    public LoginResponse login(@RequestBody LoginRequest loginReq) {
        String email = loginReq.getEmail();
        String password = loginReq.getPassword();
        logger.info("Received a login request");

        if (checkIfEmailExists(email)) {
            String name = attemptLogin(email, password);
            if (name != null) {
                LoginResponse temp = new LoginResponse();
                temp.setName(name);
                logger.info("User " + name + " logged in using email " + email);
                return temp;
            } else {
                LoginResponse temp = new LoginResponse();
                logger.info("email: " + email + " password: " + password + " is wrong combo");
                temp.setName("error");
                return temp;
            }
        } else {
            LoginResponse temp = new LoginResponse();
            logger.info("email: " + email + " password: " + password + " is wrong combo");
            temp.setName("error");
            return temp;
        }
    }

    public String attemptLogin(String email, String password) {
        String query = "SELECT * FROM users WHERE email = ? AND password = ?";
        SqlRowSet result = jdbcTemplate.queryForRowSet(query, email, password);
        if (result.next()) {
            return result.getString("name");
        } else {
            return null;
        }
    }

    /**
     * Adds user to the database.
     *
     * @param regReq Client registration request.
     * @return
     */
    @RequestMapping(path = "/register",
        consumes = "application/json",
        produces = "application/json")
    public RegisterResponse register(@RequestBody RegisterRequest regReq) {
        String email = regReq.getEmail();
        String password = regReq.getPassword();
        String name = regReq.getName();

        if (!checkIfEmailExists(email)) {
            logger.info("Email doesnt exist adding new user");
            RegisterResponse response = new RegisterResponse();
            response.setRegisterSuccess(true);
            createAccInDB(email, name, password);
            response.setName(name);
            return response;
        } else {
            logger.info("Email already exists");
            RegisterResponse response = new RegisterResponse();
            response.setRegisterSuccess(false);
            return response;
        }
    }

    public boolean checkIfEmailExists(String email) {
        String query = "SELECT * FROM users WHERE email = ?";
        SqlRowSet result = jdbcTemplate.queryForRowSet(query, email);
        if (!result.isBeforeFirst()) {
            logger.info("Email doesnt exist");
            return false;
        } else {
            if (result.next()) {
                logger.info("Email already exists under name " + result.getString("name"));
                return true;
            }
            return false;
        }
    }

    public void createAccInDB(String email, String name, String password) {
        logger.info("Added user to DB");
        String query = "INSERT INTO users (email, name, password) VALUES (?,?,?)";
        jdbcTemplate.update(query, email, name, password);
    }

    /**
     * Request mapping for adding a vegetarian meal.
     *
     * @param vegMealReq VegetarianMealRequest object that is sent from the client
     * @return Returns a json object with the user name and the amount of meals eaten.
     */

    @RequestMapping(path = "/addvegmeal",
        consumes = "application/json", produces = "application/json")
    public VegetarianMealResponse addvegmeal(@RequestBody VegetarianMealRequest vegMealReq) {
        String email = vegMealReq.getEmail();
        int amount = vegMealReq.getAmount();

        logger.info("adding vegetarian meal..");
        VegetarianMealResponse response = new VegetarianMealResponse();
        if (addVMealInDB(email, amount) == 1) {
            logger.info("vegetarian meal added successfully");
            response.setAddVegetarianMealSuccess(true);
        } else {
            logger.info("error: vegetarian meal not added");
            response.setAddVegetarianMealSuccess(false);
        }
        return response;
    }

    @RequestMapping(path = "/getVegMealsList",
        consumes = "application/json", produces = "application/json")
    public VegetarianMealListResponse getVegMealsList(@RequestBody VegetarianMealListRequest req) {
        String email = req.getEmail();

        logger.info("getting vegetarian meals for " + email);
        LinkedList<Meal> meals = findAllUserMeals(email);
        if (meals != null) {
            VegetarianMealListResponse res = new VegetarianMealListResponse();
            res.setMeals(meals);
            res.setEmail(email);
            res.setMealsListSuccess(true);
            return res;
        } else {
            VegetarianMealListResponse res = new VegetarianMealListResponse();
            res.setEmail(email);
            res.setMealsListSuccess(false);
            return res;
        }
    }

    public int getUserIdFromEmail(String email) {
        if (checkIfEmailExists(email)) {
            String query = "select * from users where email = ?";
            SqlRowSet result = jdbcTemplate.queryForRowSet(query, email);
            if (result.next()) {
                logger.info("found user id");
                return result.getInt("userid");
            } else {
                return -1;
            }
        } else {
            return -1;
        }
    }

    public int addVMealInDB(String email, int amount) {
        int userid = getUserIdFromEmail(email);
        if (userid != -1 && amount > 0) {
            String query = "INSERT INTO vegmeal (userid, time, amount) VALUES (?,?,?)";
            jdbcTemplate.update(query, userid, new Timestamp(System.currentTimeMillis()), amount);
            return 1;
        } else {
            return -1;
        }
    }

    public LinkedList<Meal> findAllUserMeals(String email) {
        int userid = getUserIdFromEmail(email);
        if (userid != -1) {
            LinkedList<Meal> mealsList = new LinkedList<>();
            SqlRowSet mealsDB = getAllMealsFromDB(userid);
            while (mealsDB.next()) {
                Meal meal = new Meal();
                meal.setMealAmount(mealsDB.getInt("amount"));
                meal.setTime(mealsDB.getTimestamp("time"));
                mealsList.add(meal);
            }
            return mealsList;
        } else {
            return null;
        }
    }

    public SqlRowSet getAllMealsFromDB(int userid) {
        String query = "SELECT * FROM VEGMEAL WHERE USERID = ?";
        SqlRowSet result = jdbcTemplate.queryForRowSet(query, userid);
        return result;
    }
}
