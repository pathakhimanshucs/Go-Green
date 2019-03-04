package client;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;


public class CreateAccountForm {


    /**
    This is the main method.
    @main method
    **/
    public static void main(String[] args) {

        JFrame newAccount = new JFrame("Create new account");
        newAccount.setSize(450,450);
        //Container c = newAccount.getContentPane();
        //c.setBackground(Color.green);
        //newAccount.setVisible(true);
        newAccount.setLayout(null);

        /*
        Implementing JLabels
         */

        JLabel createAccount = new JLabel("Create new account");
        createAccount.setBounds(150,30,200,20);
        createAccount.setFont(new Font("Arial",Font.BOLD,12));
        createAccount.setForeground(Color.BLACK);

        JLabel username = new JLabel("Username");
        username.setBounds(20,100,150,20);

        JLabel email = new JLabel("E-mail Address");
        email.setBounds(20,150,150,20);

        JLabel password = new JLabel("Password");
        password.setBounds(20,200,150,20);

        JLabel password2 = new JLabel("Confirm Password");
        password2.setBounds(20,250,150,20);

        /*
        Implementing Textfields
        */

        JTextField enterUsername = new JTextField();
        enterUsername.setBounds(130,100,200,20);

        JTextField enterEmail = new JTextField();
        enterEmail.setBounds(130,150,200,20);

        /*
        Implementing PasswordFields
         */

        JPasswordField enterPassword = new JPasswordField();
        enterPassword.setBounds(130,200,200,20);

        JPasswordField confirmPassword = new JPasswordField();
        confirmPassword.setBounds(130,250,200,20);

        /*
        Implementing Buttons
         */

        JButton pressCreateAccount = new JButton("Create account");
        pressCreateAccount.setBounds(50,325,150,30);

        JButton wrongChoice = new JButton("Already have account");
        wrongChoice.setBounds(230,325,160,30);
        /////////////////

        //JPanel panel2 = new JPanel();
        //panel2.add(enterUsername);
        //panel2.add(createAccount);
        //panel2.add(wrongChoice);
        //panel2.add(pressCreateAccount);
        //panel2.add(confirmPassword);
        //panel2.add(enterPassword);
        //panel2.add(enterEmail);
        //panel2.add(username);
        //panel2.add(email);
        //panel2.add(password);
        //panel2.add(password2);

        newAccount.add(createAccount);
        newAccount.add(enterUsername);
        newAccount.add(username);
        newAccount.add(email);
        newAccount.add(password);
        newAccount.add(password2);
        newAccount.add(enterUsername);
        newAccount.add(enterEmail);
        newAccount.add(enterPassword);
        newAccount.add(confirmPassword);
        newAccount.add(pressCreateAccount);
        newAccount.add(wrongChoice);
        newAccount.setVisible(true);
        newAccount.setLocationRelativeTo(null);
    }
}
