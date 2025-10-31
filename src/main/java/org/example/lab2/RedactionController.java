package org.example.lab2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import javafx.scene.control.Button;

public class RedactionController {

    @FXML
    private Button submitAdd;

    @FXML
    private Button cancelAdd;

    @FXML
    public void onSubmitAdd(ActionEvent event) {
        Stage stage = (Stage) submitAdd.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void onCanselAdd(ActionEvent event) {
        Stage stage = (Stage) cancelAdd.getScene().getWindow();
        stage.close();
    }
}
