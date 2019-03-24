package client.controllers;

import client.Application;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


import java.io.IOException;

public class LoginController {

    @FXML
    private JFXTextField tf_email;

    @FXML
    private JFXPasswordField pf_password;

    @FXML
    void btnLogin(MouseEvent event) {
        System.out.println("Login press");
        String username = tf_email.getText();
        String password = pf_password.getText();
        System.out.println("email " + tf_email.getText() + " password " + pf_password.getText());
        Application.loginToServer(tf_email.getText(), pf_password.getText());

        if (!Application.loginToServer(username,
            password).getName().equals("error")) {

            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("/main.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }

            Node node = (Node) event.getSource();

            Stage stage = (Stage) node.getScene().getWindow();

            stage.setScene(new Scene(root));
        }else{
            System.out.println("Alert Dialog!");
            AlertBox.display("Login Failure", "Login was unsuccessful");

        }
    }

    @FXML
    void btnRegister(MouseEvent event) {
        System.out.println("Register press");

        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/register.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Node node = (Node) event.getSource();

        Stage stage = (Stage) node.getScene().getWindow();

        stage.setScene(new Scene(root));
    }

}
