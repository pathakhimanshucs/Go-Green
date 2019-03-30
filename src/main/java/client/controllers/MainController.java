package client.controllers;

import client.Application;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import objects.FriendListResponse;

import java.io.IOException;
import java.util.LinkedList;

public class MainController {

    @FXML
    private JFXListView<Label> mylistview;

    @FXML
    private JFXListView<String> friendlistview;

    @FXML
    private JFXTextField tf_friendmail;

    @FXML
    void clickedList(MouseEvent event) {
        mylistview.getItems().add(new Label("Item"));
    }

    @FXML
    void pressPlusButton(MouseEvent event){

        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/popup.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Node node = (Node) event.getSource();

        Stage stage = (Stage) node.getScene().getWindow();

        stage.setScene(new Scene(root));

    }

    @FXML
    void friendButton(MouseEvent event) {
        String email = tf_friendmail.getText();
        boolean response = Application.addFriend(email);
        displayFriends();
        if (response == true) {
            System.out.println(response);
        } else {
            System.out.println(response);
        }
    }

    @FXML
    void displayFriends() {
       FriendListResponse response = Application.showFriends();
       if (response.isFriendsListSuccess() == false) {
           return;
       }
       LinkedList<String> friendlist = response.getFriends();
       ObservableList<String> list = FXCollections.observableList(friendlist);
       friendlistview.setItems(list);
    }
}