package  client;

import objects.RegisterResponse;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Login  {

    /**
     * Main method.
     * @param args arguments
     */
    public static void main(String[] args) {


        JFrame newAccount = new JFrame("Create new account");
        newAccount.setSize(450,450);
        Container container = newAccount.getContentPane();
        container.setBackground(Color.green);
        newAccount.setLayout(null);
        newAccount.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        //Creating Labels
        JLabel createAccount = new JLabel("Welcome to the main page!");

        createAccount.setBounds(100,30,500,20);
        createAccount.setFont(new Font("Arial", Font.BOLD, 20));
        createAccount.setForeground(Color.BLACK);

        JLabel username = new JLabel("Email");
        username.setBounds(20,100,150,20);

        JLabel password = new JLabel("Password");
        password.setBounds(20,200,150,20);

        // Creating Textfields
        JTextField enterUsername = new JTextField();
        enterUsername.setBounds(130,100,200,20);

        //Creating PasswordFields
        JPasswordField enterPassword = new JPasswordField();
        enterPassword.setBounds(130,200,200,20);

        // Creating Buttons
        JButton pressCreateAccount = new JButton("Create account");
        pressCreateAccount.setBounds(50,325,150,30);

        JButton loginChoice = new JButton("Login");
        loginChoice.setBounds(230,325,160,30);

        JPanel panel2 = new JPanel();
        panel2.add(enterUsername);
        panel2.add(createAccount);
        panel2.add(loginChoice);
        panel2.add(pressCreateAccount);
        panel2.add(enterPassword);
        panel2.add(username);
        panel2.add(password);

        newAccount.add(createAccount);
        newAccount.add(enterUsername);
        newAccount.add(username);
        newAccount.add(password);
        newAccount.add(enterUsername);
        newAccount.add(enterPassword);
        newAccount.add(pressCreateAccount);
        newAccount.add(loginChoice);
        newAccount.setVisible(true);
        newAccount.setLocationRelativeTo(null);
        //loginChoice.addActionListener(new Action1());
        loginChoice.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                String username = enterUsername.getText();
                String password = enterPassword.getText();
                System.out.println(username);
                System.out.println(password);
                if (Application.loginToServer(username,
                        password).getName().equals("error") == false) {
                    JButton logIn = new JButton();
                    logIn.addActionListener(new Action1());
                    logIn.doClick();
                }
            }
        });

        pressCreateAccount.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                String username = enterUsername.getText();
                String password = enterPassword.getText();
                System.out.println(username);
                System.out.println(password);

            }
        });

        pressCreateAccount.addActionListener(new Action2());

        newAccount.add(panel2); //Add the features in the panel into the frame

        //if (loginChoice.getModel().isPressed()) {
        ////newAccount.dispose();
        //newAccount.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
    }

    static class Action2 extends JFrame implements ActionListener {

        public void actionPerformed(ActionEvent actionEvent) {

            JFrame newAccount2 = new JFrame("Create new account");
            newAccount2.setSize(450,450);
            Container container = newAccount2.getContentPane();
            container.setBackground(Color.green);
            newAccount2.setLayout(null);

            //Creating Labels
            JLabel createAccount = new JLabel("Create new account");
            createAccount.setBounds(130,30,200,20);
            createAccount.setFont(new Font("Arial", Font.BOLD, 20));
            createAccount.setForeground(Color.BLACK);

            JLabel username = new JLabel("Username");
            username.setBounds(20,100,150,20);

            JLabel email = new JLabel("E-mail Address");
            email.setBounds(20,150,150,20);

            JLabel password = new JLabel("Password");
            password.setBounds(20,200,150,20);

            JLabel password2 = new JLabel("Confirm Password");
            password2.setBounds(20,250,150,20);

            // Creating Textfields
            JTextField enterUsername = new JTextField();
            enterUsername.setBounds(130,100,200,20);

            JTextField enterEmail = new JTextField();
            enterEmail.setBounds(130,150,200,20);

            //Creating PasswordFields
            JPasswordField enterPassword = new JPasswordField();
            enterPassword.setBounds(130,200,200,20);

            JPasswordField confirmPassword = new JPasswordField();
            confirmPassword.setBounds(130,250,200,20);

            // Creating Buttons
            JButton back = new JButton("CANCEL");
            back.setBounds(50,325,150,30);

            JButton submit = new JButton("SUBMIT");
            submit.setBounds(230,325,160,30);

            submit.addActionListener(new ActionListener() {


                @Override
                public void actionPerformed(ActionEvent ae) {
                    //JOptionPane.showMessageDialog((Component) actionEvent.getSource(),
                    // "Your have chosen container non-sustainable energy");
                    //JOptionPane.setDefaultLocale(null);
                    String username = enterUsername.getText();
                    String password = new String(enterPassword.getPassword());
                    String confirm = new String(confirmPassword.getPassword());
                    String email = enterEmail.getText();
                    RegisterResponse response = Application.createAccount(email,
                            username, password, confirm);
                    System.out.println(response.getName());
                    if (response.getRegisterSuccess() == true) {
                        JButton create = new JButton();
                        create.addActionListener(new Action1());
                        create.doClick();
                        newAccount2.setVisible(false);
                        newAccount2.dispose();
                    }
                    //if (back.getModel().isPressed()) {
                    // newAccount2.setVisible(false);
                    //newAccount.setVisible(true);

                }
            });

            back.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent ae) {
                    newAccount2.setVisible(false);
                    newAccount2.dispose();
                }
            });

            JPanel panel2 = new JPanel();
            panel2.add(enterUsername);
            panel2.add(createAccount);
            panel2.add(submit);
            panel2.add(back);
            panel2.add(confirmPassword);
            panel2.add(enterPassword);
            panel2.add(enterEmail);
            panel2.add(username);
            panel2.add(email);
            panel2.add(password);
            panel2.add(password2);

            newAccount2.add(createAccount);
            newAccount2.add(enterUsername);
            newAccount2.add(username);
            newAccount2.add(email);
            newAccount2.add(password);
            newAccount2.add(password2);
            newAccount2.add(enterUsername);
            newAccount2.add(enterEmail);
            newAccount2.add(enterPassword);
            newAccount2.add(confirmPassword);
            newAccount2.add(submit);
            newAccount2.add(back);
            newAccount2.setVisible(true);
            newAccount2.setLocationRelativeTo(null);
            newAccount2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            //back.addActionListener(new Action1());

            //LoginChoice.addActionListener(new Action1());
            newAccount2.add(panel2); //Add the features in the panel into the frame


            // Submit.addActionListener(new Action1());
            //Submit.addActionListener(new ActionListener() {
            //pressCreateAccount.addActionListener(new Action2());
            //pressCreateAccount.addActionListener(new ActionListener() {
            //@Override
            //public void actionPerformed(ActionEvent ae) {
            //String username =enterUsername.getText();
            //String password = enterPassword.getText();
            //System.out.println(username);
            //System.out.println(password);
            //}});
            //newAccount2.add(panel2); //Add the features in the panel into the frame
        }

    }
}
