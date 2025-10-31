package org.example.lab2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    public Button submitAdd;
    @FXML
    public Button cancelAdd;
    public Button OtherLabsBtn;
    public Button Exit;
    public VBox MainVbox;
    @FXML
    private Button addButton;
    @FXML
    private Button redactionButton;
    @FXML
    private Button deleteButton;
    @FXML
    private Label label;

    @FXML
    public void showRedactionWindow(javafx.event.ActionEvent actionEvent) {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("RedactionWindow.fxml"));

            try{
                Stage stage = new Stage();
                Scene scene = new Scene(fxmlLoader.load(), 600, 200);
                stage.setScene(scene);

                Image icon = new Image(getClass().getResource("/images/app_icon.png").toExternalForm());
                stage.getIcons().add(icon);

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

        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image(getClass().getResource("/images/app_icon.png").toExternalForm()));

        DialogPane dialogPane = alert.getDialogPane();

        String cssPath = getClass().getResource("/styles/AlertStyles.css").toExternalForm();
        dialogPane.getStylesheets().add(cssPath);
        var result = alert.showAndWait().orElse(null);

        if(result == null){
            this.label.setText("No Selection!");
        } else if (result == ButtonType.OK) {
            this.label.setText("Запис видалено!");
        } else if (result == ButtonType.CANCEL) {
            this.label.setText("Відмінено!");
        } else {
            this.label.setText("-");
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){

        OtherLabsBtn.setOnAction(actionEvent ->{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("OtherLabs.fxml"));
            Stage stage = new Stage();
            Scene scene = null;
            Image icon = new Image(getClass().getResource("/images/app_icon.png").toExternalForm());
            stage.getIcons().add(icon);
            try{
                scene = new Scene(fxmlLoader.load(),600,600);
            }catch (IOException e){
                e.printStackTrace();
            }
            stage.setTitle("Інші практичні");
            stage.setResizable(false);
            stage.setScene(scene);
            stage.show();

        });
    }

    @FXML
    public void onSubmitAdd(ActionEvent actionEvent) {
        Stage stage = (Stage) submitAdd.getScene().getWindow();
        stage.close();

    }
    @FXML
    public void onCanselAdd(ActionEvent actionEvent) {
        Stage stage = (Stage) submitAdd.getScene().getWindow();
        stage.close();

    }


}

