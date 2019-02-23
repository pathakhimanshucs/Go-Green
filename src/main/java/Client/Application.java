package Client;


import java.io.IOException;
import java.io.OutputStream;
import java.util.Scanner;
import java.net.HttpURLConnection;
import java.net.URL;

public class Application
{

    public static void main(String[] args)
    {
        System.out.println("Please enter your name");
        Scanner sc = new Scanner(System.in);
        Request("name="+sc.nextLine());
        sc.close();

    }

    public static void Request(String str)
    {
        try
        {
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
        }
        catch(IOException e)
        {
            System.out.println("Server is down");
            System.exit(1);
        }
    }

}
