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

import java.security.SecureRandom;
import java.sql.Timestamp;
import java.util.Base64;
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
                temp.setToken(generateAuthToken(email));
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

    /**
     * attemptLogin method.
     * @param email string
     * @param password string
     * @return String
     */
    public String attemptLogin(String email, String password) {
        String query = "SELECT * FROM users WHERE email = ?";

        SqlRowSet result = jdbcTemplate.queryForRowSet(query, email);
        if (result.next()) {
            String userSentPassWord = Encrypt.decryptPassWord(email, password);
            String dbReturnedEncryption = result.getString("password");
            String dbReturnedPassWord = Encrypt.decryptPassWord(email, dbReturnedEncryption);
            if(userSentPassWord.equals(dbReturnedPassWord)) {
                return result.getString("name");
            } else {
                return null;
            }
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

    /**
     * checkIfEmailExists method.
     * @param email String
     * @return boolean
     */

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

    /**
     * createAccInDB method.
     * @param email String
     * @param name String
     * @param password String
     */
    public void createAccInDB(String email, String name, String password) {
        logger.info("Added user to DB");
        String query = "INSERT INTO users (email, name, password) VALUES (?,?,?)";
        jdbcTemplate.update(query, email, name, password);
    }

    /**
     * Request mapping for adding a vegetarian meal.
     *
     * @return Returns a json object with the user name and the amount of meals eaten.
     */

    @RequestMapping(path = "/addactivity",
        consumes = "application/json", produces = "application/json")
    public ActivityResponse addActivity(@RequestBody ActivityRequest actReq) {
        String email = actReq.getEmail();
        int amount = actReq.getAmount();
        Activity activity = actReq.getActivity();

        logger.info("adding activity...");
        ActivityResponse response = new ActivityResponse();
        if (addActivityInDB(email, amount, activity) == 1) {
            logger.info("activity added successfully");
            response.setAddActivitySuccess(true);
        } else {
            logger.info("error: activity not added");
            response.setAddActivitySuccess(false);
        }
        return response;
    }

    /**
     * method.
     * @param req ActivityListRequest
     * @return
     */
    @RequestMapping(path = "/getActivityList",
        consumes = "application/json", produces = "application/json")
    public ActivityListResponse getActivityList(@RequestBody ActivityListRequest req) {
        String email = req.getEmail();

        logger.info("getting activities " + email);
        LinkedList<Activity> activities = findAllActivities(email);
        if (activities != null) {
            ActivityListResponse res = new ActivityListResponse();
            res.setActivities(activities);
            res.setEmail(email);
            res.setActivityListSuccess(true);
            return res;
        } else {
            ActivityListResponse res = new ActivityListResponse();
            res.setEmail(email);
            res.setActivityListSuccess(false);
            return res;
        }
    }


    /**
     * getUserIdFromEmail method.
     * @param email String
     * @return
     */
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

    /**
     * addActivityInDB method.
     * @param email String
     * @param amount integer
     * @param activity Activity
     * @return
     */
    public int addActivityInDB(String email, int amount, Activity activity) {
        int userid = getUserIdFromEmail(email);
        String activityType = "";

        switch(activity.getActivity()) {
            case VEGMEAL: activityType = "vegmeal";
                break;
            case LOCALFOOD: activityType = "localfood";
                break;
            case BIKE: activityType = "bike";
                break;
            case PUBTRANS: activityType = "pubtrans";
                break;
            case HOMETEMP: activityType = "hometemp";
                break;
            case SOLARPANELS: activityType = "solarpanels";
                break;
        }

        if (userid != -1 && amount > 0) {
            String query = "INSERT INTO ? (userid, time, amount) VALUES (?,?,?)";
            jdbcTemplate.update(query, activityType, userid, new Timestamp(System.currentTimeMillis()), amount);

            return 1;
        } else {
            return -1;
        }
    }
    /**
     * findAllActivities method.
     * @param email String
     * @return LinkedList
     */
    public LinkedList<Activity> findAllActivities(String email) {
        int userid = getUserIdFromEmail(email);
        if (userid != -1) {
            LinkedList<Activity> activitiesList = new LinkedList<>();
            SqlRowSet activitiesDB = getAllActivitiesFromDB(userid);
            while (activitiesDB.next()) {
                Activity activity = new Activity();
                activity.setAmount(activitiesDB.getInt("amount"));
                activity.setTime(activitiesDB.getTimestamp("time"));
                activity.setActivity(Activity.activityObject.valueOf(activitiesDB.getString("table_name")));
                activitiesList.add(activity);
            }
            return activitiesList;
        } else {
            return null;
        }
    }

    /**
     * getAllActivitiesFromDB method.
     * @param userid integer
     * @return SqlRowSet
     */
    public SqlRowSet getAllActivitiesFromDB(int userid) {
        String query = "SELECT * FROM activities WHERE userid = ?";
        SqlRowSet result = jdbcTemplate.queryForRowSet(query, userid);
        return result;
    }

    @RequestMapping(path = "/addFriend",
        consumes = "application/json", produces = "application/json")
    public AddFriendResponse addFriend(@RequestBody AddFriendRequest addReq) {
        String friend1 = addReq.getFriend1email();
        String friend2 = addReq.getFriend2email();

        logger.info("Attempting to add friends");
        if(addFriendsToDB(friend1, friend2) != -1) {
            AddFriendResponse res = new AddFriendResponse();
            res.setAddFriendSuccess(true);
            res.setFriend2(friend2);
            return res;
        }else{
            AddFriendResponse res = new AddFriendResponse();
            res.setAddFriendSuccess(false);
            return res;
        }
    }

    private int addFriendsToDB(String friend1email, String friend2email){
        int friend1 = getUserIdFromEmail(friend1email);
        int friend2 = getUserIdFromEmail(friend2email);

        if(friend1 == -1 || friend2 == -1){
            return -1;
        }else{
            String query = "INSERT INTO friends (friend1, friend2) VALUES (?,?)";
            jdbcTemplate.update(query, friend1,friend2);
            return 1;
        }
    }

    private LinkedList<String> getFriends(String email){
        int friend1ID = getUserIdFromEmail(email);
        if(friend1ID != -1){
            SqlRowSet result = getAllFriendsFromDB(friend1ID);
            LinkedList<String> friendsList = new LinkedList<>();
            while (result.next()) {
                int friend = result.getInt("friend2");
                String friendEmail = getEmailFromUserID(friend);
                friendsList.add(friendEmail);
            }
            return friendsList;
        }else{
            return null;
        }
    }

    private String getEmailFromUserID(int userId) {
        String query = "select * from users where userid = ?";
        SqlRowSet result = jdbcTemplate.queryForRowSet(query, userId);
        if (result.next()) {
            logger.info("found email");
            return result.getString("email");
        } else {
            return null;
        }
    }

    private SqlRowSet getAllFriendsFromDB(int userid){
        String query = "SELECT * FROM friends WHERE friend1 = ?";
        SqlRowSet result = jdbcTemplate.queryForRowSet(query, userid);
        return result;
    }

    @RequestMapping(path = "/getFriendsList",
        consumes = "application/json", produces = "application/json")
    public FriendListResponse getFriendsList(@RequestBody FriendsListRequest req) {
        String email = req.getEmail();
        AuthToken token = req.getToken();

        if(!checkTokenValidity(token.getToken())){
            FriendListResponse res = new FriendListResponse();
            res.setEmail(email);
            res.setFriendsListSuccess(false);
            return res;
        }

        logger.info("getting friends for" + email);
        LinkedList<String> friends = getFriends(email);
        if (friends != null) {
            FriendListResponse res = new FriendListResponse();
            res.setEmail(email);
            res.setFriendsListSuccess(true);
            res.setFriends(friends);
            return res;
        } else {
            FriendListResponse res = new FriendListResponse();
            res.setEmail(email);
            res.setFriendsListSuccess(false);
            return res;
        }
    }

    private AuthToken generateAuthToken(String email){
        removeDuplicateToken(email);
        removeExpiredTokens();

        SecureRandom random = new SecureRandom();
        byte[] bytes = new byte[20];
        random.nextBytes(bytes);
        String token = Base64.getEncoder().encodeToString(bytes);
        addTokenToDB(email, token);
        logger.info(token);
        AuthToken resToken = new AuthToken();
        resToken.setEmail(email);
        resToken.setToken(token);
        return resToken;
    }

    private void addTokenToDB(String email, String token){
        String query = "INSERT INTO sessiontokens (email, time, token) VALUES (?,?,?)";
        jdbcTemplate.update(query, email, new Timestamp(System.currentTimeMillis()), token);
    }

    private void removeDuplicateToken(String email){
        String query = "SELECT remove_duplicate_tokens(?)";
        SqlRowSet result = jdbcTemplate.queryForRowSet(query, email);
    }
    private void removeExpiredTokens(){
        String query = "SELECT remove_expired_tokens()";
        SqlRowSet result = jdbcTemplate.queryForRowSet(query);
    }

    private boolean checkTokenValidity(String token){
        removeExpiredTokens();

        String query = "SELECT * FROM sessiontokens WHERE token = ?";
        SqlRowSet result = jdbcTemplate.queryForRowSet(query, token);

        if (!result.isBeforeFirst()) {
            logger.info("Token doesnt exist");
            return false;
        } else {
            if (result.next()) {
                logger.info("Token exists");
                return true;
            }
            return false;
        }
    }

}
