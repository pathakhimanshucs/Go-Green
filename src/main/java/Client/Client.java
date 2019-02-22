package Client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        String userName = args[0];
        String passWord = args[1];

        Socket socket = new Socket("localhost", 4000 );
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
        Login login = new Login(userName, passWord);
        objectOutputStream.writeObject(login);
        Login returnLogin = (Login)objectInputStream.readObject();

        socket.close();
    }
}
