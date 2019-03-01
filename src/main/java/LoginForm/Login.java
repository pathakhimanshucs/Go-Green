package LoginForm;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Login {

    public static void main(String[] args) {

        //Scanner scan = new Scanner(System.in);

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

        //String textFieldValue = textfield1.getText();
        //System.out.print(textFieldValue);

        JPanel panel = new JPanel();
        panel.add(label1);
        panel.add(label2);
        panel.add(label3);
        panel.add(textfield1);
        panel.add(passwordField);
        panel.add(button1);

        frame1.add(textfield1);
        frame1.add(passwordField);
        frame1.add(button1);
        frame1.add(label1);
        frame1.add(label2);
        frame1.add(label3);
        frame1.setVisible(true);
        frame1.setResizable(true);
        frame1.setLayout(null);
        frame1.setLocationRelativeTo(null);
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//			    button1.addActionListener(new Action());
//			    frame1.add(panel); //Add the features in the panel into the frame

        button1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                String username =textfield1.getText();
                String password = passwordField.getText();
                System.out.println(username);
                System.out.println(password);

//					if (textfield1.getText() == null && passwordField.getText() == null) {
//						JOptionPane.showMessageDialog(null, "Please make sure all fields are filled in");

            }
        });

        button1.addActionListener(new Action());
        frame1.add(panel); //Add the features in the panel into the frame
    }



    //ActionListener once the submit button is pressed
    static class Action implements ActionListener {

        public void actionPerformed(ActionEvent e) {

            JFrame Frame2 = new JFrame("GO-GREEN");
            Frame2.setSize(600,600);
            Frame2.setLayout(new FlowLayout());
            Frame2.setLocationRelativeTo(null);
            Frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            Frame2.setResizable(true);
            Frame2.setVisible(true);

            JLabel label3 = new JLabel("Kilometers travelled:");
            //JLabel label4 = new JLabel("After entering your password, "
            //+ "please press on the submit button:" + "\n\n\n");

            JTextField textfield2 = new JTextField();
            textfield2.setPreferredSize(new Dimension(200,25));

            textfield2.setVisible(true);
            textfield2.setEnabled(true);

            //Combobox and dropdown menubar
            String Planet[]={"", "Transport", "Food", "Energy"};
            JComboBox Environment = new JComboBox(Planet);
            Environment.setBounds(60,60,90,20);
            Frame2.add(Environment);

            String Vehicle[]={""};
            JComboBox VehicleType = new JComboBox(Vehicle);
            VehicleType.setBounds(60,60,90,20);
            Frame2.add(VehicleType);

            String CarSelection[]={""};
            JComboBox Cars = new JComboBox(CarSelection);
            Cars.setBounds(60,60,90,20);
            Frame2.add(Cars);

            //Tick Box- Check box
            JCheckBox checkbox = new JCheckBox();
            checkbox.setText("Do you wish to proceed?");
            checkbox.setVisible(true);
            checkbox.setSelected(true);


            //Button
            JButton button2 = new JButton("SUBMIT");
            button2.setSize(100,100);
            button2.setEnabled(true);
            button2.setVisible(true);


            JPanel panel2 = new JPanel();
            panel2.add(button2);
            panel2.add(label3);
            //panel2.add(label4);
            panel2.add(textfield2);
            panel2.add(checkbox);
            button2.addActionListener(new Action2());
            Frame2.add(panel2);
        }

        static class Action2 implements ActionListener {

            public void actionPerformed(ActionEvent e) {

                JFrame Frame3 = new JFrame("GO-GREEN");
                Frame3.setSize(600,600);
                Frame3.setLocationRelativeTo(null);
                Frame3.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                Frame3.setResizable(false);
                Frame3.setVisible(true);

                JPanel panel = new JPanel();

                //panel.add(button2);
                //button2.addActionListener(new Action());
                Frame3.add(panel); //Add the features in the panel

                //private void createUIComponents() {
                // TODO: place custom component creation code here
            }
        }}}



