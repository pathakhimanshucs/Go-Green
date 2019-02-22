package LoginForm;

import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Login extends JFrame {

    public static void main(String[] args) {

        JFrame frame1 = new JFrame("GO-GREEN!");
        frame1.setSize(400, 300);
        frame1.setBackground(Color.green);

        JLabel label1 = new JLabel("Login");
        label1.setBounds(180, 30, 400, 30);

        JLabel label2 = new JLabel("Username");
        label2.setBounds(0, 70, 200, 30);

        JLabel label3 = new JLabel("Password");
        label3.setBounds(80, 40, 200, 30);

        JTextField textfield1 = new JTextField();
        textfield1.setBounds(150, 70, 200, 30);

        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(150, 110, 200, 30);

        JButton button1 = new JButton("Login");
        button1.setBounds(150, 160, 100, 30);

        frame1.add(textfield1);
        frame1.add(passwordField);
        frame1.add(button1);
        frame1.add(label1);
        frame1.add(label2);
        frame1.add(label3);

        frame1.setVisible(true);
        frame1.setResizable(true);
        frame1.setLayout(null);

    }
}
