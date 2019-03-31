package client.controllers;

import client.Application;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DialogPane;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {

    @FXML
    private JFXTextField tfemail;

    @FXML
    private JFXPasswordField pfpassword;

    @FXML
    void btnLogin(MouseEvent event) {
        System.out.println("Login press");
        String username = tfemail.getText();
        String password = pfpassword.getText();
        System.out.println("email " + tfemail.getText() + " password " + pfpassword.getText());

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
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Login Failure");
            alert.setContentText("Login was unsuccessful");
            alert.setHeaderText(null);
            DialogPane dialogPane = alert.getDialogPane();
            dialogPane.getStylesheets().add(
                getClass().getResource("/dark-theme.css").toExternalForm());
            alert.showAndWait();
            System.out.println("Alert Dialog!");
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
