package client.controllers;

import client.Application;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import objects.Activity;

import java.io.IOException;

public class ActivityController {
    private Activity.ActivityObject activityType;

    @FXML
    private JFXTextField tfamount;

    @FXML
    private MenuButton dropdown;

    @FXML
    private Label description;

    @FXML
    void setBike() {
        activityType = Activity.ActivityObject.BIKE;
        dropdown.setText("BIKE (km) ");
        description.setText("Amount of km travelled by bike (km) : ");
    }

    @FXML
    void setVegmeal() {
        activityType = Activity.ActivityObject.VEGMEAL;
        dropdown.setText("VEGMEAL (0.2 kg) ");
        description.setText("Amount of vegetarian meals (portions of 0.2 kg) : " );
    }

    @FXML
    void setSolarpanels() {
        activityType = Activity.ActivityObject.SOLARPANELS;
        dropdown.setText("SOLARPANELS (m^2) ");
        description.setText("Amount of solarpanels installed (1m^2 /panel) : ");
    }

    @FXML
    void setLocalfood() {
        activityType = Activity.ActivityObject.LOCALFOOD;
        dropdown.setText("LOCALFOOD (kg) ");
        description.setText("Mass of local produced food bought (portions of 1 kg) : ");
    }

    @FXML
    void setHometemp() {
        activityType = Activity.ActivityObject.HOMETEMP;
        dropdown.setText("HOMETEMP (°C) ");
        description.setText("Amount of degrees the home temperature is lowered in (°C) : ");
    }

    @FXML
    void setPubtrans() {
        activityType = Activity.ActivityObject.PUBTRANS;
        dropdown.setText("PUBTRANS (km) ");
        description.setText("Amount of km travelled with public transport (km) : ");
    }



    @FXML
    void addActivity(MouseEvent event) {
        System.out.println("press");
        if (activityType == null) {
            return;
        }

        if (tfamount.equals("")) {
            return;
        }
        int value = Integer.parseInt(tfamount.getText());
        if (Application.addActivity(value, activityType) == true) {
            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("/main.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }

            Node node = (Node) event.getSource();

            Stage stage = (Stage) node.getScene().getWindow();

            stage.setScene(new Scene(root));
        } else {
            return;
        }
    }

    @FXML
    void backMain(MouseEvent event) {

        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/main.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Node node = (Node) event.getSource();

        Stage stage = (Stage) node.getScene().getWindow();

        stage.setScene(new Scene(root));
    }



}