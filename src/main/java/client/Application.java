package client;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

/**
 * Client application with main method.
 */
public class Application {
    /**
     * Main method.
     * @param args Provided arguments.
     */
    public static void main(String[] args) {
        System.out.println("Please enter your name");
        Scanner sc = new Scanner(System.in);
        request("name=" + sc.nextLine());
        sc.close();
    }

    /**
     * Sends an HTTP request to the server with str as param.
     * @param str Name to be displayed.
     */
    public static void request(String str) {
        try {
            URL url = new URL("http://localhost:8080/request");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();

            con.setRequestMethod("GET");
            con.setDoOutput(true);

            OutputStream os = con.getOutputStream();
            os.write(str.getBytes());
            os.flush();
            os.close();

            Scanner sc = new Scanner(con.getInputStream());
            String output = sc.nextLine();
            System.out.println(output);
            con.disconnect();
            sc.close();
        } catch (IOException e) {
            System.out.println("Server is down");
            System.exit(1);
        }
    }
}
