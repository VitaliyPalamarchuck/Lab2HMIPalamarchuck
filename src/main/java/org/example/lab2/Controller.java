package org.example.lab2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class Controller {
    @FXML
    private Button addButton;
    @FXML
    private Button redactionButton;
    @FXML
    private Button deleteButton;
    @FXML
    private Label label;

    @FXML
    public void showDialog(javafx.event.ActionEvent actionEvent) {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("RedactionWindow.fxml"));

            try{
                Stage stage = new Stage();
                Scene scene = new Scene(fxmlLoader.load(), 600, 200);
                stage.setScene(scene);

                stage.setTitle("Redaction Window!");
                stage.setResizable(false);
                stage.setMinWidth(600);
                stage.setMinHeight(600);
                stage.setMaxWidth(600);
                stage.setMaxHeight(150);
                stage.initModality(Modality.WINDOW_MODAL);
                stage.initOwner(addButton.getScene().getWindow());

                stage.show();
            }catch (IOException e){
                e.printStackTrace();
            }
        }

    @FXML
    void information_Alert(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Видалення");
        alert.setContentText("Ви впевненні, що хочете видалити запис?");

        if(alert.showAndWait().get()==null){
            this.label.setText("No Selection!");
        } else if (alert.showAndWait().get() == ButtonType.OK) {
            this.label.setText("Запис видалено!");
        }else if (alert.showAndWait().get() == ButtonType.CANCEL) {
            this.label.setText("Відмінено!");
        }else {
            this.label.setText("-");
        }
    }

}

