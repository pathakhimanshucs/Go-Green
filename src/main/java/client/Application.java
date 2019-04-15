package client;

import objects.Activity;
import objects.ActivityListRequest;
import objects.ActivityListResponse;
import objects.ActivityRequest;
import objects.ActivityResponse;
import objects.AddFriendRequest;
import objects.AddFriendResponse;
import objects.AuthToken;
import objects.Encrypt;
import objects.FriendListResponse;
import objects.FriendsListRequest;
import objects.LoginRequest;
import objects.LoginResponse;
import objects.RegisterRequest;
import objects.RegisterResponse;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.Locale;

/**
 * Client application that contains all necessary api calls.
 */
public class Application {
    //static String eMail;
    static AuthToken token;


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
        if (login.getName().equals("error") == false) {
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
    public static boolean addActivity(int amount, Activity.ActivityObject activityobj) {
        final String baseUrl = "http://localhost:" + 8080 + "/addactivity/";
        URI uri = null;
        try {
            uri = new URI(baseUrl);
        } catch (URISyntaxException e) {
            System.err.println(e);
        }

        Activity activity = new Activity();
        activity.setAmount(amount);
        Date date = new Date();
        Timestamp time = new Timestamp(date.getTime());
        activity.setTime(time);
        activity.setActivity(activityobj);
        activity.setCo2Amount(1);

        ActivityRequest actReq = new ActivityRequest();
        actReq.setToken(token);
        actReq.setEmail(token.getEmail());
        actReq.setActivity(activity);

        HttpHeaders headers = new HttpHeaders();
        HttpEntity<ActivityRequest> req = new HttpEntity<>(actReq, headers);
        RestTemplate restTemplate = new RestTemplate();
        ActivityResponse response = restTemplate.postForObject(uri,
                req, ActivityResponse.class);

        if (response.isAddActivitySuccess()) {
            System.out.println("Succesfully added veg meal to db");
            return true;
        } else {
            return false;
        }
    }

    /**
     * Retrieves vergmeals from server.
     * @return Object.
     */
    public static ActivityListResponse getActivities() {
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
        /*
        int size = res.getActivities().size();
        Object[][] data = new Object[size][2];
        for (int i = 0; i < size; i++) {
            Activity curr = res.getActivities().get(i);
            String time = timeFormatter(curr.getTime());
            int amount = curr.getAmount();
            data[i][0] = time;
            data[i][1] = amount;
        } */
        return res;
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

        if (friendEmail.equals(token.getEmail())) {
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

    /**
     * Reqeusts server for friendlist.
     * @return
     */
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

    /**
     * calculates the amount of CO2 one saves in terms of food consumption.
     * @return a float with the amount of CO2 he/she has saved
     */
    public static float calculateFood() {
        ActivityListResponse activities =  Application.getActivities();
        LinkedList<Activity> getFood = activities.getActivities();
        int countValue = 0;
        float sumFood = 0;
        while (countValue < getFood.size()) {
            if (getFood.get(countValue).getActivity().equals(Activity.ActivityObject.VEGMEAL)
                    || getFood.get(countValue).getActivity()
                    .equals(Activity.ActivityObject.LOCALFOOD)) {
                sumFood += getFood.get(countValue).getCo2Amount();
            }
            countValue++;
        }
        return sumFood;
    }
    /**
     * calculates the amount of CO2 one saved in terms of transport.
     * @return a float with the amount of CO2 he/she has saved
     */

    public static float calculatePubTransport() {
        ActivityListResponse activities =  Application.getActivities();
        LinkedList<Activity> getPubTransport = activities.getActivities();
        int countValue = 0;
        float sumPubTransport = 0;
        while (countValue < getPubTransport.size()) {
            if (getPubTransport.get(countValue).getActivity()
                    .equals(Activity.ActivityObject.BIKE)
                    ||
                    getPubTransport.get(countValue).getActivity()
                            .equals(Activity.ActivityObject.PUBTRANS)) {
                sumPubTransport += getPubTransport.get(countValue).getCo2Amount();
            }
            countValue++;
        }
        return sumPubTransport;
    }
    /**
     * calculates the amount of CO2 one saved in terms of energy.
     * @return a float with the amount of CO2 he/she has saved
     */

    public static float calculateEnergy() {
        ActivityListResponse activities =  Application.getActivities();
        LinkedList<Activity> getEnergy = activities.getActivities();
        int countValue = 0;
        float sumEnergy = 0;
        while (countValue < getEnergy.size()) {
            if (getEnergy.get(countValue).getActivity()
                    .equals(Activity.ActivityObject.SOLARPANELS)
                    || getEnergy.get(countValue).getActivity()
                    .equals(Activity.ActivityObject.HOMETEMP)) {
                sumEnergy += getEnergy.get(countValue).getCo2Amount();
            }
            countValue++;
        }
        return sumEnergy;
    }


}
