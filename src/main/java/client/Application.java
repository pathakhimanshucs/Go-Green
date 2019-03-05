package client;

import objects.LoginRequest;
import objects.LoginResponse;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * Client application with main method.
 */
public class Application {
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
        LoginRequest loginReq = new LoginRequest();
        loginReq.setEmail(email);
        loginReq.setPassword(password);

        HttpHeaders headers = new HttpHeaders();

        HttpEntity<LoginRequest> request = new HttpEntity<>(loginReq, headers);
        RestTemplate restTemplate = new RestTemplate();
        LoginResponse login = restTemplate.postForObject(uri, request, LoginResponse.class);
        return login;
    }
}
