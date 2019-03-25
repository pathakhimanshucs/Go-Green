package client.controllers;

import com.jfoenix.controls.JFXListView;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class MainController {

    @FXML
    private JFXListView<Label> mylistview;

    @FXML
    void clickedList(MouseEvent event) {
        mylistview.getItems().add(new Label("Item"));
    }

}