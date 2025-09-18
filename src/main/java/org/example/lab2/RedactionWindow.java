package org.example.lab2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


public class RedactionWindow extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("RedactionWindow.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 400, 100);
        stage.setMinWidth(450);
        stage.setMinHeight(140);
        stage.setMaxWidth(450);
        stage.setMaxHeight(140);
        stage.setTitle("Redaction Window!");
        stage.setScene(scene);
        stage.show();
    }


    public static void main(String[] args) {
        launch();
    }
}
