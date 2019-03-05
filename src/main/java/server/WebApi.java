package server;

import objects.LoginRequest;
import objects.LoginResponse;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


@RestController
public class WebApi {
    @RequestMapping("/request")
    public String request(@RequestParam(value = "name", defaultValue = "Anonymous") String name) {
        System.out.println("Received a request");
        return "Hello " + name;
    }

    /**
     * Request mapping for login function.
     * @param loginReq LoginRequest object that is sent from the client
     * @return Returns a json object with the user name if the email and password are correct.
     */
    @RequestMapping(path = "/login", consumes = "application/json", produces = "application/json")
    public LoginResponse login(@RequestBody LoginRequest loginReq) {
        String email = loginReq.getEmail();
        String password = loginReq.getPassword();
        System.out.println("Received a request");
        Connection connection = null;
        PreparedStatement stmt = null;
        String name = null;
        String query = "select userid, name from users where email = ? AND password = ?;";
        try {
            connection = DriverManager
                .getConnection("jdbc:postgresql://104.248.88.37:5432/gogreenserver",
                    "postgres", "admin");

            stmt = connection.prepareStatement(query);
            stmt.setString(1, email);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("userid");
                name = rs.getString("name");
                System.out.println("ID = " + id);
                System.out.println("NAME = " + name);
            }
            rs.close();
            stmt.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        if (name == null) {
            LoginResponse temp = new LoginResponse();
            temp.setName("error");
            return temp;
        } else {
            LoginResponse temp = new LoginResponse();
            temp.setName(name);
            return temp;
        }
    }
}
