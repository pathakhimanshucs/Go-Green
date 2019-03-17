package  client;

import objects.RegisterResponse;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Login  {

    public static void main(String[] args) {


        JFrame newAccount = new JFrame("Create new account");
        newAccount.setSize(450,450);
        Container a = newAccount.getContentPane();
        a.setBackground(Color.green);
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

        JButton LoginChoice = new JButton("Login");
        LoginChoice.setBounds(230,325,160,30);

        JPanel panel2 = new JPanel();
        panel2.add(enterUsername);
        panel2.add(createAccount);
        panel2.add(LoginChoice);
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
        newAccount.add(LoginChoice);
        newAccount.setVisible(true);
        newAccount.setLocationRelativeTo(null);
        //LoginChoice.addActionListener(new Action1());
        LoginChoice.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                String username =enterUsername.getText();
                String password = enterPassword.getText();
                System.out.println(username);
                System.out.println(password);
                if (Application.loginToServer(username, password).getName().equals("error") == false)
                {
                    JButton logIn = new JButton();
                    logIn.addActionListener(new Action1());
                    logIn.doClick();
                }
            }});

        pressCreateAccount.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                String username =enterUsername.getText();
                String password = enterPassword.getText();
                System.out.println(username);
                System.out.println(password);

            }});

        pressCreateAccount.addActionListener(new Action2());

        newAccount.add(panel2); //Add the features in the panel into the frame

//			    if (LoginChoice.getModel().isPressed()) {
//			    	//newAccount.dispose();
//			    	newAccount.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
    }

    static class Action2 extends JFrame implements ActionListener {

        public void actionPerformed(ActionEvent e) {

            JFrame newAccount2 = new JFrame("Create new account");
            newAccount2.setSize(450,450);
            Container a = newAccount2.getContentPane();
            a.setBackground(Color.green);
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
//				JOptionPane.showMessageDialog((Component) e.getSource(), "Your have chosen a non-sustainable energy");
//		        JOptionPane.setDefaultLocale(null);
                    String username = enterUsername.getText();
                    String password = new String(enterPassword.getPassword());
                    String confirm = new String(confirmPassword.getPassword());
                    String email = enterEmail.getText();
                    RegisterResponse response = Application.createAccount(email, username, password, confirm);
                    System.out.println(response.getName());
                    if(response.getRegisterSuccess()== true){
                        JButton create = new JButton();
                        create.addActionListener(new Action1());
                        create.doClick();
                        newAccount2.setVisible(false);
                        newAccount2.dispose();
                    }


//					if (back.getModel().isPressed()) {
//			           newAccount2.setVisible(false);
//			           newAccount.setVisible(true);

                }});

            back.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent ae) {
                    newAccount2.setVisible(false);
                    newAccount2.dispose();
                }});

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
//				Submit.addActionListener(new ActionListener() {
//
////			pressCreateAccount.addActionListener(new Action2());
////			pressCreateAccount.addActionListener(new ActionListener() {
//
//				@Override
//				public void actionPerformed(ActionEvent ae) {
//					String username =enterUsername.getText();
//					String password = enterPassword.getText();
//					System.out.println(username);
//					System.out.println(password);

//				}});

//				newAccount2.add(panel2); //Add the features in the panel into the frame

        }

    }
}

//ActionListener once the submit button is pressed
class Action1 extends JFrame implements ActionListener {


    public void actionPerformed(ActionEvent e) {

        JFrame Frame2 = new JFrame("GO-GREEN");
        Frame2.setSize(600,600);
        Frame2.setLayout(null);
        Frame2.setLocationRelativeTo(null);
        Frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Frame2.setResizable(false);
        Frame2.setVisible(true);

        Container b = Frame2.getContentPane();
        b.setBackground(Color.green);

        JLabel label3 = new JLabel("Please select your food-option:");
        label3.setBounds(200, 50, 250, 25);

        JButton Vegetarian = new JButton("Vegetarian meal");
        Vegetarian.setBounds(100,80,200,30);
        Vegetarian.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ///////////////////////////
                Application.addVegMeal(1);
                JOptionPane.showMessageDialog((Component) e.getSource(), "You have chosen a Vegetarian meal");
                JOptionPane.setDefaultLocale(null);
            }
        });

        JButton NonVegetarian = new JButton("Non-Vegetarian meal");
        NonVegetarian.setBounds(300,80,200,30);
        NonVegetarian.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog((Component) e.getSource(), "You have chosen a non-vegetarian meal");
                JOptionPane.setDefaultLocale(null);
            }
        });

        JLabel label4 = new JLabel("Please select your transportation-option:");
        label4.setBounds(200, 150, 250, 25);

        JButton SustainableTransportation = new JButton("Sustainable Transport");
        SustainableTransportation.setBounds(100,180,210,30);
        SustainableTransportation.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog((Component) e.getSource(), "You have chosen Sustainable transport");
                JOptionPane.setDefaultLocale(null);
            }
        });

        JButton NonSustainableTransportion = new JButton("Non-sustainable transport");
        NonSustainableTransportion.setBounds(300,180,220,30);
        NonSustainableTransportion.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog((Component) e.getSource(), "You have chosen non-sustainable transport");
                JOptionPane.setDefaultLocale(null);
            }
        });

        JLabel label5 = new JLabel("Please select your energy-option:");
        label5.setBounds(200, 250, 250, 25);

        JButton SustainableEnergy = new JButton("Sustainable Energy");
        SustainableEnergy.setBounds(120,300,200,30);
        SustainableEnergy.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog((Component) e.getSource(), "You have chosen sustainable energy");
                JOptionPane.setDefaultLocale(null);
            }
        });

        JButton NonSustainableEnergy = new JButton("Non-Sustainable Energy");
        NonSustainableEnergy.setBounds(300,300,200,30);
        NonSustainableEnergy.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog((Component) e.getSource(), "You have chosen a non-sustainable energy");
                JOptionPane.setDefaultLocale(null);
            }
        });

//			        //Button
//					JButton button2 = new JButton("SUBMIT");
//					button2.setSize(100,100);
//					button2.setBounds(210,500,160,50);
//					button2.setEnabled(true);
//					button2.setVisible(true);

        JPanel panel2 = new JPanel();
        panel2.setLayout(new FlowLayout());
        panel2.add(label3);
        panel2.add(label4);
        panel2.add(label5);

//					panel2.add(button2);
        panel2.add(Vegetarian);
        panel2.add(SustainableTransportation);
        panel2.add(NonSustainableTransportion);
        panel2.add(SustainableEnergy);
        panel2.add(NonSustainableEnergy);

        Frame2.add(label3);
        Frame2.add(label4);
        Frame2.add(label5);
        Frame2.add(Vegetarian);
        Frame2.add(NonVegetarian);
        Frame2.add(SustainableTransportation);
        Frame2.add(NonSustainableTransportion);
        Frame2.add(SustainableEnergy);
        Frame2.add(NonSustainableEnergy);

        Frame2.setVisible(true);
        Frame2.setResizable(true);
        Frame2.setLayout(null);
        Frame2.setLocationRelativeTo(null);
        Frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Frame2.setBackground(Color.green);

//					button2.addActionListener(new Action2());
        Vegetarian.addActionListener(new Action2());
        NonVegetarian.addActionListener(new Action2());
        SustainableTransportation.addActionListener(new Action2());
        NonSustainableTransportion.addActionListener(new Action2());
        SustainableEnergy.addActionListener(new Action2());
        NonSustainableEnergy.addActionListener(new Action2());

    }

    class Action2 extends JFrame implements ActionListener  {

        public void actionPerformed(ActionEvent e) {

            JFrame Frame3 = new JFrame("GO-GREEN");
            Frame3.setSize(600,600);
            Frame3.setLocationRelativeTo(null);
            Frame3.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            Frame3.setResizable(false);
            Frame3.setVisible(true);

            Container c = Frame3.getContentPane();
            c.setBackground(Color.green);

            JLabel label6 = new JLabel("");
            label6.setBounds(100,30,500,20);

            JPanel panel3 = new JPanel();
            panel3.setLayout(new FlowLayout());
            panel3.add(label6);
            Frame3.add(label6);

            Frame3.setVisible(true);
            Frame3.setResizable(true);
            Frame3.setLayout(null);
            Frame3.setLocationRelativeTo(null);
            Frame3.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            Frame3.setBackground(Color.green);

            //button2.addActionListener(new Action());
            Frame3.add(panel3); //Add the features in the panel
        }
//// DEMO 2 GUI PART
    }
}