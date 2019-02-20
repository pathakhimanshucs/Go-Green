import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static final int PORT = 4000;

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        new Server().runServer();
    }

    public void runServer() throws IOException, ClassNotFoundException{
        ServerSocket serverSocket = new ServerSocket(PORT);
        System.out.println("Server up and ready for connections .");
        Socket socket = serverSocket.accept();
        ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
        Login login = (Login)objectInputStream.readObject();
        doSomething(login);
        objectOutputStream.writeObject(login);
        socket.close();
    }

    public void doSomething(Login login){
        System.out.println("This is the username that is given : " + login.getUserName());
        System.out.println("This is the password that is given : " + login.getPassWord());

    }

}
