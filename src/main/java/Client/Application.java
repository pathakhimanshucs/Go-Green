package Client;

import java.io.IOException;
import java.io.*;
import java.util.Scanner;
import java.net.HttpURLConnection;
import java.net.URL;

public class Application
{

    public static void main(String[] args)
    {

        Request();

    }

    public static void Request()
    {
        try
        {
            String output;
            URL url = new URL("http://localhost:8080/request");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            Scanner sc = new Scanner(con.getInputStream());
            output = sc.nextLine();
            System.out.println(output);

          //  con.disconnect();


        }
        catch(IOException e)
        {
            System.exit(1);
        }
    }

}
