package client.controllers;

import client.Application;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
    void setBike() {
        activityType = Activity.ActivityObject.BIKE;
        dropdown.setText("BIKE");
    }

    @FXML
    void setVegmeal() {
        activityType = Activity.ActivityObject.VEGMEAL;
        dropdown.setText("VEGMEAL");
    }

    @FXML
    void setSolarpanels() {
        activityType = Activity.ActivityObject.SOLARPANELS;
        dropdown.setText("SOLARPANELS");
    }

    @FXML
    void setLocalfood() {
        activityType = Activity.ActivityObject.LOCALFOOD;
        dropdown.setText("LOCALFOOD");
    }

    @FXML
    void setHometemp() {
        activityType = Activity.ActivityObject.HOMETEMP;
        dropdown.setText("HOMETEMP");
    }

    @FXML
    void setPubtrans() {
        activityType = Activity.ActivityObject.PUBTRANS;
        dropdown.setText("PUBTRANS");
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