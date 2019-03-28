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
import objects.RegisterResponse;

import java.io.IOException;

public class RegisterController {

    @FXML
    private JFXTextField tf_name;

    @FXML
    private JFXTextField tf_email;

    @FXML
    private JFXPasswordField pf_password;

    @FXML
    void backLogin(MouseEvent event) {

        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/login.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Node node = (Node) event.getSource();

        Stage stage = (Stage) node.getScene().getWindow();

        stage.setScene(new Scene(root));
    }

    @FXML
    void btnRegister(MouseEvent event) {
        RegisterResponse response = Application.createAccount(tf_email.getText(),
            tf_name.getText(), pf_password.getText(), pf_password.getText());

        if (response.getRegisterSuccess() == true) {
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
            alert.setTitle("Register Failure");
            alert.setContentText("Registration was unsuccessful");
            alert.setHeaderText(null);
            DialogPane dialogPane = alert.getDialogPane();
            dialogPane.getStylesheets().add(
                    getClass().getResource("/dark-theme.css").toExternalForm());
            alert.showAndWait();
            System.out.println("Alert Dialog!");
        }
    }

}
