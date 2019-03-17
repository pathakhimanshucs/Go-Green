package client;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

//ActionListener once the submit button is pressed
class Action1 extends JFrame implements ActionListener {


    public void actionPerformed(ActionEvent actionEvent) {

        JFrame frame2 = new JFrame("GO-GREEN");
        frame2.setSize(600,600);
        frame2.setLayout(null);
        frame2.setLocationRelativeTo(null);
        frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame2.setResizable(false);
        frame2.setVisible(true);

        Container container = frame2.getContentPane();
        container.setBackground(Color.green);

        JLabel label3 = new JLabel("Please select your food-option:");
        label3.setBounds(200, 50, 250, 25);

        JButton vegetarian = new JButton("vegetarian meal");
        vegetarian.setBounds(100,80,200,30);
        vegetarian.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent1) {
                ///////////////////////////
                Application.addVegMeal(1);
                JOptionPane.showMessageDialog((Component) actionEvent1.getSource(),
                        "You have chosen a vegetarian meal");
                JOptionPane.setDefaultLocale(null);
            }
        });

        JButton nonVegetarian = new JButton("Non-vegetarian meal");
        nonVegetarian.setBounds(300,80,200,30);
        nonVegetarian.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent1) {
                JOptionPane.showMessageDialog((Component) actionEvent1.getSource(),
                        "You have chosen a non-vegetarian meal");
                JOptionPane.setDefaultLocale(null);
            }
        });

        JLabel label4 = new JLabel("Please select your transportation-option:");
        label4.setBounds(200, 150, 250, 25);

        JButton sustainableTransportation = new JButton("Sustainable Transport");
        sustainableTransportation.setBounds(100,180,210,30);
        sustainableTransportation.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent1) {
                JOptionPane.showMessageDialog((Component) actionEvent1.getSource(),
                        "You have chosen Sustainable transport");
                JOptionPane.setDefaultLocale(null);
            }
        });

        JButton nonSustainableTransportion = new JButton("Non-sustainable transport");
        nonSustainableTransportion.setBounds(300,180,220,30);
        nonSustainableTransportion.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent1) {
                JOptionPane.showMessageDialog((Component) actionEvent1.getSource(),
                        "You have chosen non-sustainable transport");
                JOptionPane.setDefaultLocale(null);
            }
        });

        JLabel label5 = new JLabel("Please select your energy-option:");
        label5.setBounds(200, 250, 250, 25);

        JButton sustainableEnergy = new JButton("Sustainable Energy");
        sustainableEnergy.setBounds(120,300,200,30);
        sustainableEnergy.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent1) {
                JOptionPane.showMessageDialog((Component) actionEvent1.getSource(),
                        "You have chosen sustainable energy");
                JOptionPane.setDefaultLocale(null);
            }
        });

        JButton nonSustainableEnergy = new JButton("Non-Sustainable Energy");
        nonSustainableEnergy.setBounds(300,300,200,30);
        nonSustainableEnergy.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent1) {
                JOptionPane.showMessageDialog((Component) actionEvent1.getSource(),
                        "You have chosen a non-sustainable energy");
                JOptionPane.setDefaultLocale(null);
            }
        });

        //Button
        //JButton button2 = new JButton("SUBMIT");
        //button2.setSize(100,100);
        //button2.setBounds(210,500,160,50);
        //button2.setEnabled(true);
        //button2.setVisible(true);

        JPanel panel2 = new JPanel();
        panel2.setLayout(new FlowLayout());
        panel2.add(label3);
        panel2.add(label4);
        panel2.add(label5);

        //panel2.add(button2);
        panel2.add(vegetarian);
        panel2.add(sustainableTransportation);
        panel2.add(nonSustainableTransportion);
        panel2.add(sustainableEnergy);
        panel2.add(nonSustainableEnergy);

        frame2.add(label3);
        frame2.add(label4);
        frame2.add(label5);
        frame2.add(vegetarian);
        frame2.add(nonVegetarian);
        frame2.add(sustainableTransportation);
        frame2.add(nonSustainableTransportion);
        frame2.add(sustainableEnergy);
        frame2.add(nonSustainableEnergy);

        frame2.setVisible(true);
        frame2.setResizable(true);
        frame2.setLayout(null);
        frame2.setLocationRelativeTo(null);
        frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame2.setBackground(Color.green);

        //button2.addActionListener(new Action2());
        vegetarian.addActionListener(new client.Action1.Action2());
        nonVegetarian.addActionListener(new client.Action1.Action2());
        sustainableTransportation.addActionListener(new client.Action1.Action2());
        nonSustainableTransportion.addActionListener(new client.Action1.Action2());
        sustainableEnergy.addActionListener(new client.Action1.Action2());
        nonSustainableEnergy.addActionListener(new client.Action1.Action2());

    }

    class Action2 extends JFrame implements ActionListener  {

        public void actionPerformed(ActionEvent actionEvent) {

            JFrame frame3 = new JFrame("GO-GREEN");
            frame3.setSize(600,600);
            frame3.setLocationRelativeTo(null);
            frame3.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame3.setResizable(false);
            frame3.setVisible(true);

            Container container = frame3.getContentPane();
            container.setBackground(Color.green);

            JLabel label6 = new JLabel("");
            label6.setBounds(100,30,500,20);

            JPanel panel3 = new JPanel();
            panel3.setLayout(new FlowLayout());
            panel3.add(label6);
            frame3.add(label6);

            frame3.setVisible(true);
            frame3.setResizable(true);
            frame3.setLayout(null);
            frame3.setLocationRelativeTo(null);
            frame3.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame3.setBackground(Color.green);

            //button2.addActionListener(new Action());
            frame3.add(panel3); //Add the features in the panel
        }
        //// DEMO 2 GUI PART
    }
}