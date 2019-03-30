package client;

import objects.*;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * Client application with main method.
 */
public class Application {
    //static String eMail;
    static AuthToken token;
    /**
     * Main method.
     * @param args Provided arguments.
     */

    public static void main(String[] args) {
        System.out.println("Welcome " + loginToServer("alice@gmail.com","alicepwd").toString());
    }

    /**
     * Sends an HTTP request to the server with str as param.
     * @param email Email of the login
     * @param password Password of the login
     */
    public static LoginResponse loginToServer(String email, String password) {
        final String baseUrl = "http://localhost:" + 8080 + "/login/";
        URI uri = null;
        try {
            uri = new URI(baseUrl);
        } catch (URISyntaxException e) {
            System.err.println(e);
        }
        String newEmail = email.toLowerCase();
        LoginRequest loginReq = new LoginRequest();
        loginReq.setEmail(newEmail);
        loginReq.setPassword(Encrypt.encryptPassWord(email, password));

        HttpHeaders headers = new HttpHeaders();
        HttpEntity<LoginRequest> request = new HttpEntity<>(loginReq, headers);
        RestTemplate restTemplate = new RestTemplate();
        LoginResponse login = restTemplate.postForObject(uri, request, LoginResponse.class);
        if (login.getName().equals("error") == false){
            token = login.getToken();
            System.out.println(token.getToken());
            System.out.println(token.getEmail());
            System.out.println("Logged in");
        }
        return login;
    }

    /**
     * Creates an account on the database.
     * @param email Email of user.
     * @param name Name of user.
     * @param password Password of user.
     * @param confirm Confirmation of password.
     * @return Returns the response of the server.
     */

    public static RegisterResponse createAccount(String email, String name ,
                                                 String password, String confirm) {
        final String baseUrl = "http://localhost:" + 8080 + "/register/";
        URI uri = null;
        try {
            uri = new URI(baseUrl);
        } catch (URISyntaxException e) {
            System.err.println(e);
        }
        RegisterResponse response = new RegisterResponse();
        if (password.equals(confirm) == false) {
            response.setName("Failed to create account: Password and confirmation does not match");
            response.setRegisterSuccess(false);
            return response;
        }
        if (password.equals("") || email.equals("") || name.equals("")) {
            response.setName("Please fill in all fields");
            response.setRegisterSuccess(false);
            return response;
        }
        String newEmail = email.toLowerCase();
        RegisterRequest registerReq = new RegisterRequest();
        registerReq.setEmail(newEmail);
        registerReq.setName(name);
        registerReq.setPassword(Encrypt.encryptPassWord(email,password));

        HttpHeaders headers = new HttpHeaders();
        HttpEntity<RegisterRequest> req = new HttpEntity<>(registerReq, headers);
        RestTemplate restTemplate = new RestTemplate();
        RegisterResponse responseMessage = restTemplate.postForObject(uri,
                req, RegisterResponse.class);
        if (responseMessage.registerSuccess == false) {
            response.setName("Failed to create account");
            response.setRegisterSuccess(false);
            return response;
        } else {
            response.setName("Account " + responseMessage.getName() + " created");
            response.setRegisterSuccess(true);
            token = responseMessage.getToken();
            return response;
        }
    }

    /**
     * adds Veg Meal method.
     * @param amount of vegmeal
     */
    public static void addActivity(int amount) {
        final String baseUrl = "http://localhost:" + 8080 + "/addactiviy/";
        URI uri = null;
        try {
            uri = new URI(baseUrl);
        } catch (URISyntaxException e) {
            System.err.println(e);
        }

        ActivityRequest actReq = new ActivityRequest();
        actReq.getActivity().setAmount(amount);
        actReq.setToken(token);
        actReq.setEmail(token.getEmail());

        HttpHeaders headers = new HttpHeaders();
        HttpEntity<ActivityRequest> req = new HttpEntity<>(actReq, headers);
        RestTemplate restTemplate = new RestTemplate();
        ActivityResponse response = restTemplate.postForObject(uri,
                req, ActivityResponse.class);

        if (response.isAddActivitySuccess()) {
            System.out.println("Succesfully added veg meal to db");
        }
    }

    /**
     * Retrieves vergmeals from server.
     * @return Object.
     */
    public static Object[][] getActivities() {
        final String baseUrl = "http://localhost:" + 8080 + "/getActivityList/";
        URI uri = null;
        try {
            uri = new URI(baseUrl);
        } catch (URISyntaxException e) {
            System.err.println(e);
        }

        ActivityListRequest req = new ActivityListRequest();
        req.setToken(token);
        req.setEmail(token.getEmail());

        RestTemplate restTemplate = new RestTemplate();
        ActivityListResponse res = restTemplate.postForObject(uri,
            req, ActivityListResponse.class);

        int size = res.getActivities().size();
        Object[][] data = new Object[size][2];
        for (int i = 0; i < size; i++) {
            Activity curr = res.getActivities().get(i);
            String time = timeFormatter(curr.getTime());
            int amount = curr.getAmount();
            data[i][0] = time;
            data[i][1] = amount;
        }
        return data;
    }

    private static String timeFormatter(Timestamp time) {
        String pattern = "EEEEE dd MMMMM yyyy HH:mm";
        SimpleDateFormat simpleDateFormat =
            new SimpleDateFormat(pattern, new Locale("en", "US"));

        return simpleDateFormat.format(time);
    }

    /**
     * Adds friend.
     * @param email Email of friend.
     * @return
     */
    public static boolean addFriend(String email) {
        final String baseUrl = "http://localhost:" + 8080 + "/addFriend/";
        URI uri = null;
        try {
            uri = new URI(baseUrl);
        } catch (URISyntaxException e) {
            System.err.println(e);
        }

        String friendEmail = email.toLowerCase();

        if (friendEmail.equals(token.getEmail())){
            return false;
        }


        AddFriendRequest fr = new AddFriendRequest();
        fr.setFriend1email(token.getEmail());
        fr.setFriend2email(friendEmail);
        fr.setToken(token);

        HttpHeaders headers = new HttpHeaders();
        HttpEntity<AddFriendRequest> req = new HttpEntity<>(fr, headers);
        RestTemplate restTemplate = new RestTemplate();
        AddFriendResponse response = restTemplate.postForObject(uri,
                req, AddFriendResponse.class);

        if (response.isAddFriendSuccess()) {
            return true;
        } else {
            return false;
        }
    }

    public static FriendListResponse showFriends() {
        final String baseUrl = "http://localhost:" + 8080 + "/getFriendsList/";
        URI uri = null;
        try {
            uri = new URI(baseUrl);
        } catch (URISyntaxException e) {
            System.err.println(e);
        }

        FriendsListRequest fr = new FriendsListRequest();
        fr.setEmail(token.getEmail());
        fr.setToken(token);

        HttpHeaders headers = new HttpHeaders();
        HttpEntity<FriendsListRequest> req = new HttpEntity<>(fr, headers);
        RestTemplate restTemplate = new RestTemplate();
        FriendListResponse response = restTemplate.postForObject(uri,
                req, FriendListResponse.class);
        return response;
    }


}
