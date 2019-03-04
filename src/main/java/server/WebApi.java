package server;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;


@RestController
public class WebApi {

    @RequestMapping("/request")
    public String request(@RequestParam(value = "name", defaultValue = "Anonymous") String name) {
        System.out.println("Received a request");
        return "Hello " + name;
    }

    /**
     * login method.
     * @param email info
     * @param password info
     * @return map
     */
    @RequestMapping("/login")
    public HashMap<String, String> login(
        @RequestParam(value = "email", defaultValue = "kek@gmail.com") String email,
        @RequestParam(value = "password", defaultValue = "abcd") String password) {
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
            HashMap<String, String> map = new HashMap<>();
            map.put("name", "error");
            return map;
        } else {
            HashMap<String, String> map = new HashMap<>();
            map.put("name", name);
            return map;
        }
    }
}
