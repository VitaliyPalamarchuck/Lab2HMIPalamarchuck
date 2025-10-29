package org.example.lab2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class OtherLabsController {

    @FXML
    private Button Button;

    @FXML
    private ImageView ImageView;

    @FXML
    private Label Label;

    Image image = new Image(getClass().getResource("/images/otherimg2.jpg").toExternalForm());

    @FXML
    void changeLabel (ActionEvent event){
        ImageView.setImage(image);
        Label.setText("Ви успішно змінили картинку!");
        Button.setDisable(true);
    }
}
