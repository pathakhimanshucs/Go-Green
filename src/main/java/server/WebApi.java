package server;

import objects.*;
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

    private String attemptLogin(String email, String password) {
        String query = "SELECT * FROM users WHERE email = ? AND password = ?";
        SqlRowSet result = jdbcTemplate.queryForRowSet( query, email, password);
        if (result.next()) {
            return result.getString("name");
        } else {
            return null;
        }
    }

    /**
     * Adds user to the database.
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

    private boolean checkIfEmailExists(String email) {
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

    private void createAccInDB(String email, String name, String password) {
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
    @RequestMapping(path = "/addvegmeal", consumes = "application/json", produces = "application/json")
    public VegetarianMealResponse addvegmeal(@RequestBody VegetarianMealRequest vegMealReq) {
        String email = vegMealReq.getEmail();
        int amount = vegMealReq.getAmount();

        logger.info("adding vegetarian meal..");
        VegetarianMealResponse response = new VegetarianMealResponse();
        if ( addVMealInDB(email,amount) == 1) {
            logger.info("vegetarian meal added successfully");
            response.setAddVegetarianMealSuccess(true);
        } else {
            logger.info("error: vegetarian meal not added");
            response.setAddVegetarianMealSuccess(false);
        }
        return response;
    }

    private int getUserIdFromEmail(String email) {
        if (checkIfEmailExists(email)) {
            String query = "select userid from users where email = ?";
            SqlRowSet result = jdbcTemplate.queryForRowSet(query, email);
            return result.getInt("userid");
        } else {
            return -1;
        }
    }

    private int addVMealInDB(String email, int amount) {
        int userid = getUserIdFromEmail(email);
        if (userid != -1 && amount > 0) {
            String query = "INSERT INTO vegmeal (userid, time, amount) VALUES (?,?,?)";
            jdbcTemplate.update(query, userid, new Timestamp(System.currentTimeMillis()), amount);
            return 1;
        } else {
            return -1;
        }
    }
}
