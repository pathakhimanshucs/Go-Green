package client;

import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;



////////////////////////////// FRAME WHEN THE LOGIN BUTTON IS PRESSED //////////////////////////////

//ActionListener once the submit button is pressed

public class Action1 extends JFrame implements ActionListener {

    /**
     * actionPerformed method.
     * @param actionEvent parameter
     */
    public void actionPerformed(ActionEvent actionEvent) {

        JFrame frame2 = new JFrame("GO-GREEN");
        frame2.setSize(600,600);
        //frame2.setLayout(new FlowLayout());
        frame2.setLayout(null);
        frame2.setLocationRelativeTo(null);
        frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame2.setResizable(false);
        frame2.setVisible(true);

        Container container = frame2.getContentPane();
        container.setBackground(Color.green);

        JLabel label3 = new JLabel("Please select your food-option:");
        label3.setBounds(200, 50, 250, 25);

        String food[] = {"SELECT:", "Vegetarian-food", "Non-Vegetarian food"};
        JComboBox sustainableFood = new JComboBox(food);
        sustainableFood.setBounds(150,100,300,20);
        frame2.add(sustainableFood);

        JCheckBox checkbox1 = new JCheckBox();
        checkbox1.setText("I choose the food-option");
        checkbox1.setBounds(200,125,170,30);
        checkbox1.setVisible(true);
        checkbox1.setSelected(true);

        JLabel label4 = new JLabel("Please select your transportation-option:");
        label4.setBounds(200, 150, 250, 25);

        String transport[] = {"SELECT:", "Sustainable transport", "Non-sustainable transport"};
        JComboBox sustainableTransport = new JComboBox(transport);
        sustainableTransport.setBounds(150,200,300,20);
        frame2.add(sustainableTransport);

        JCheckBox checkbox2 = new JCheckBox();
        checkbox2.setText("I choose the transport-option");
        checkbox2.setBounds(200,225,170,30);
        checkbox2.setVisible(true);
        checkbox2.setSelected(true);

        JLabel label5 = new JLabel("Please select your energy-option:");
        label5.setBounds(200, 250, 250, 25);

        String energy[] = {"SELECT:", "Sustainable energy", "Non-sustainable energy"};
        JComboBox sustainableEnergy = new JComboBox(energy);
        sustainableEnergy.setBounds(150,300,300,20);
        frame2.add(sustainableEnergy);

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
        panel2.add(sustainableFood);
        panel2.add(sustainableTransport);
        panel2.add(sustainableEnergy);
        panel2.add(button2);
        frame2.add(label3);
        frame2.add(label4);
        frame2.add(label5);
        frame2.add(sustainableFood);
        frame2.add(sustainableTransport);
        frame2.add(sustainableEnergy);
        frame2.add(checkbox);
        frame2.add(checkbox1);
        frame2.add(checkbox2);
        frame2.add(checkbox3);
        frame2.add(button2);
        frame2.setVisible(true);
        frame2.setResizable(true);
        frame2.setLayout(null);
        frame2.setLocationRelativeTo(null);
        frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame2.setBackground(Color.green);

        //if () {
        //
        //}


        button2.addActionListener(new Action1.Action2());
        // frame2.add(panel2);
    }

    ////////////////////// FRAME AFTER THE PREVIOUS FRAME///////////////////////////

    static class Action2 extends JFrame implements ActionListener  {

        public void actionPerformed(ActionEvent actionEvent) {

            JFrame frame3 = new JFrame("GO-GREEN");
            frame3.setSize(600,600);
            frame3.setLocationRelativeTo(null);
            frame3.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame3.setResizable(false);
            frame3.setVisible(true);

            Container container = frame3.getContentPane();
            container.setBackground(Color.green);

            frame3.setBackground(Color.green);
            JPanel panel = new JPanel();
            //button2.addActionListener(new Action());
            frame3.add(panel); //Add the features in the panel
        }

    }
}
