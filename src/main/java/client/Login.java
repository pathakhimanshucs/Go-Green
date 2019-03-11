package client;

import java.awt.Color;
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

public class Login {

    public static void main(String[] args) {


/////////////////////////////////////////// FRAME 1 /////////////////////////////////////////////////


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

        JLabel username = new JLabel("Username");
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

        JButton LoginChoice = new JButton("login");
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
        LoginChoice.addActionListener(new Action1());
        LoginChoice.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                String username =enterUsername.getText();
                String password = enterPassword.getText();
                System.out.println(username);
                System.out.println(password);
                System.out.println("Welcome " + Application.loginToServer(username, password).getName());

            }});


        pressCreateAccount.addActionListener(new Action2());
        //LoginChoice.addActionListener(new Action1());
        newAccount.add(panel2); //Add the features in the panel into the frame

//			    if (LoginChoice.getModel().isPressed()) {
//			    	//newAccount.dispose();
//			    	newAccount.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
//			    }

    }

////////////////////////////////// FRAME IF CREATE AN ACCOUNT IS CHOSEN //////////////////////////////


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
            JButton Submit = new JButton("SUBMIT");
            Submit.setBounds(50,325,150,30);

            JButton back = new JButton("Back");
            back.setBounds(230,325,160,30);

//		        if (Submit.getModel().isPressed()) {
////		        	JOptionPane.showMessageDialog(null, "Your details are now saved!", "Welcome", JOptionPane.INFORMATION_MESSAGE);
//		        JOptionPane.showInputDialog("Eggs are not supposed to be green.");
//		        }
//		        	JOptionPane.showMessageDialog(null,  "Awesome Video Keep it up",
//		        			"Thank you",JOptionPane.PLAIN_MESSAGE);?
//		        	back.setEnabled(true);
//			         newAccount2.setVisible(false);


            JPanel panel2 = new JPanel();
            panel2.add(enterUsername);
            panel2.add(createAccount);
            panel2.add(Submit);
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
            newAccount2.add(Submit);
            newAccount2.add(back);
            newAccount2.setVisible(true);
            newAccount2.setLocationRelativeTo(null);
            newAccount2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            // back.addActionListener(new Action1());
            back.addActionListener(new ActionListener() {


                @Override
                public void actionPerformed(ActionEvent ae) {
                    String username =enterUsername.getText();
                    String password = enterPassword.getText();
                    System.out.println(username);
                    System.out.println(password);

                }});

            Submit.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent ae) {
                    String username = enterUsername.getText();
                    String password = new String(enterPassword.getPassword());
                    String confirm = new String(confirmPassword.getPassword());
                    String email = enterEmail.getText();
                    String response = Application.createAccount(email, username, password, confirm);
                    System.out.println(response);
                }});



            //back.addActionListener(new Action1());
            //LoginChoice.addActionListener(new Action1());
            newAccount2.add(panel2); //Add the features in the panel into the frame


            // Submit.addActionListener(new Action1());
            Submit.addActionListener(new ActionListener() {

//			pressCreateAccount.addActionListener(new Action2());
//			pressCreateAccount.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent ae) {
                    String username =enterUsername.getText();
                    String password = enterPassword.getText();
                    System.out.println(username);
                    System.out.println(password);

                }});

            newAccount2.add(panel2); //Add the features in the panel into the frame

            //newAccount.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        }


    }
}


////////////////////////////// FRAME WHEN THE LOGIN BUTTON IS PRESSED //////////////////////////////

//ActionListener once the submit button is pressed
class Action1 extends JFrame implements ActionListener {

    public void actionPerformed(ActionEvent e) {

        JFrame Frame2 = new JFrame("GO-GREEN");
        Frame2.setSize(600,600);
        //Frame2.setLayout(new FlowLayout());
        Frame2.setLayout(null);
        Frame2.setLocationRelativeTo(null);
        Frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Frame2.setResizable(false);
        Frame2.setVisible(true);

        Container b = Frame2.getContentPane();
        b.setBackground(Color.green);

        JLabel label3 = new JLabel("Please select your food-option:");
        label3.setBounds(200, 50, 250, 25);

        String Food[]={"SELECT:", "Vegetarian-food", "Non-Vegetarian food"};
        JComboBox SustainableFood = new JComboBox(Food);
        SustainableFood.setBounds(150,100,300,20);
        Frame2.add(SustainableFood);

        JCheckBox checkbox1 = new JCheckBox();
        checkbox1.setText("I choose the food-option");
        checkbox1.setBounds(200,125,170,30);
        checkbox1.setVisible(true);
        checkbox1.setSelected(true);

        JLabel label4 = new JLabel("Please select your transportation-option:");
        label4.setBounds(200, 150, 250, 25);

        String Transport[]={"SELECT:", "Sustainable Transport", "Non-sustainable transport"};
        JComboBox SustainableTransport = new JComboBox(Transport);
        SustainableTransport.setBounds(150,200,300,20);
        Frame2.add(SustainableTransport);

        JCheckBox checkbox2 = new JCheckBox();
        checkbox2.setText("I choose the Transport-option");
        checkbox2.setBounds(200,225,170,30);
        checkbox2.setVisible(true);
        checkbox2.setSelected(true);

        JLabel label5 = new JLabel("Please select your energy-option:");
        label5.setBounds(200, 250, 250, 25);

        String Energy[]={"SELECT:", "Sustainable Energy", "Non-sustainable Energy"};
        JComboBox SustainableEnergy = new JComboBox(Energy);
        SustainableEnergy.setBounds(150,300,300,20);
        Frame2.add(SustainableEnergy);

        JCheckBox checkbox3 = new JCheckBox();
        checkbox3.setText("I choose the energy-option");
        checkbox3.setBounds(200,350,170,30);
        checkbox3.setVisible(true);
        checkbox3.setSelected(true);

        //Tick Box- Check box
        JCheckBox checkbox = new JCheckBox();
        checkbox.setText("Do you wish to proceed?");
        checkbox.setBounds(200,400,170,30);
        checkbox.setVisible(true);
        checkbox.setSelected(true);

        //Button
        JButton button2 = new JButton("SUBMIT");
        button2.setSize(100,100);
        button2.setBounds(210,500,160,50);
        button2.setEnabled(true);
        button2.setVisible(true);

        JPanel panel2 = new JPanel();
        panel2.setLayout(new FlowLayout());
        panel2.add(label3);
        panel2.add(label4);
        panel2.add(label5);
        panel2.add(checkbox);
        panel2.add(checkbox1);
        panel2.add(checkbox2);
        panel2.add(checkbox3);
        panel2.add(SustainableFood);
        panel2.add(SustainableTransport);
        panel2.add(SustainableEnergy);
        panel2.add(button2);
        Frame2.add(label3);
        Frame2.add(label4);
        Frame2.add(label5);
        Frame2.add(SustainableFood);
        Frame2.add(SustainableTransport);
        Frame2.add(SustainableEnergy);
        Frame2.add(checkbox);
        Frame2.add(checkbox1);
        Frame2.add(checkbox2);
        Frame2.add(checkbox3);
        Frame2.add(button2);
        Frame2.setVisible(true);
        Frame2.setResizable(true);
        Frame2.setLayout(null);
        Frame2.setLocationRelativeTo(null);
        Frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Frame2.setBackground(Color.green);

//						if () {
//
//						}


        button2.addActionListener(new Action2());
        // Frame2.add(panel2);
    }

////////////////////////////// FRAME AFTER THE PREVIOUS FRAME///////////////////////////////////////

    static class Action2 extends JFrame implements ActionListener  {

        public void actionPerformed(ActionEvent e) {

            JFrame Frame3 = new JFrame("GO-GREEN");
            Frame3.setSize(600,600);
            Frame3.setLocationRelativeTo(null);
            Frame3.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            Frame3.setResizable(false);
            Frame3.setVisible(true);

            Container c = Frame3.getContentPane();
            c.setBackground(Color.green);

            Frame3.setBackground(Color.green);
            JPanel panel = new JPanel();
            //button2.addActionListener(new Action());
            Frame3.add(panel); //Add the features in the panel
        }


    }
}
