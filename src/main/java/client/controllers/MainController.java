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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import objects.Activity;
import objects.ActivityListResponse;
import objects.Friend;
import objects.FriendListResponse;

//import javax.swing.text.html.ImageView;
import java.io.IOException;
import java.util.LinkedList;

public class MainController {

    @FXML
    private JFXListView<Label> mylistview;

    @FXML
    private TableView<Friend> friendTable;

    @FXML
    private JFXTextField tffriendmail;

    @FXML
    private TableView<Activity> tableview;

    @FXML
    private TableColumn activityColumn1;

    @FXML
    private TableColumn activityColumn2;

    @FXML
    private TableColumn activityColumn3;

    @FXML
    private TableColumn activityColumn4;

    @FXML
    private TableColumn friendColumn;

    @FXML
    private TableColumn friendco2Column;

    @FXML
    private Label treesSaved;

    @FXML
    private ImageView tree1;

    @FXML
    private ImageView tree2;

    @FXML
    private ImageView tree3;

    @FXML
    private ImageView tree4;

    @FXML
    private ImageView tree5;




    @FXML
    void clickedList(MouseEvent event) {

        //---------------------------------------------------------------

        //mylistview.getItems().add(new Label("Item"));
       //Application.addActivity(1, "VEGMEAL");

        //--------------------------------------------------------------




    }

    @FXML
    void pressPlusButton(MouseEvent event) {

        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/Activity.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Node node = (Node) event.getSource();

        Stage stage = (Stage) node.getScene().getWindow();

        stage.setScene(new Scene(root));

    }

    @FXML
    void friendButton(MouseEvent event) {
        String email = tffriendmail.getText();
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
        friendColumn.setCellValueFactory(new PropertyValueFactory<Friend, String>("email"));
        friendco2Column.setCellValueFactory(new PropertyValueFactory<Friend, Float>("totalCO2"));
        LinkedList<Friend> friendlist = response.getFriends();
        ObservableList<Friend> list = FXCollections.observableList(friendlist);
        friendTable.setItems(list);
    }

    @FXML
    void displayActivity() {
        activityColumn1.setCellValueFactory(new PropertyValueFactory<Activity, String>("activity"));
        activityColumn2.setCellValueFactory(new PropertyValueFactory<Activity, Integer>("amount"));
        activityColumn3.setCellValueFactory(new PropertyValueFactory<Activity, String>("time"));
        activityColumn4.setCellValueFactory(new PropertyValueFactory<Activity, Float>("co2Amount"));
        ActivityListResponse data = Application.getActivities();
        LinkedList<Activity> list = data.getActivities();
        ObservableList<Activity> observableList = FXCollections.observableList(list);
        tableview.setItems(observableList);
    }
    @FXML
    void totalCo2() {
        ActivityListResponse data = Application.getActivities();
        LinkedList<Activity> list = data.getActivities();
        float total = 0;
        for (int i = 0; i < list.size(); i++){
            total = total + list.get(i).getCo2Amount();
        }
//treesSaved//

        double savedtrees = Math.floor(total/20000);
        long roundedTrees = Math.round(savedtrees);
//        String trees = Double.toString(savedtrees);
//        int treeInt = Integer.parseInt(trees);
        if (roundedTrees == 1) {
            treesSaved.setText("You saved " + roundedTrees + " tree");
        } else {
            treesSaved.setText("You saved " + roundedTrees + " trees");
        }
        //treesSaved.setAlignment(center);

        if (roundedTrees >= 1) {
            tree1.setVisible(true);
        }

        if (roundedTrees >= 2) {
            tree2.setVisible(true);
        }

        if (roundedTrees >= 3) {
            tree3.setVisible(true);
        }

        if (roundedTrees >= 4) {
            tree4.setVisible(true);
        }

        if (roundedTrees >= 5) {
            tree5.setVisible(true);
        }
    }

}