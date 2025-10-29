package org.example.lab2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Mainwindow.fxml"));
        stage.setMinHeight(600);
        stage.setMinWidth(720);
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        scene.getStylesheets().add(Main.class.getResource("/styles/Mainwindow.css").toExternalForm());
        Image icon = new Image(getClass().getResource("/images/app_icon.png").toExternalForm());
        stage.getIcons().add(icon);
        stage.setTitle("Адресна книга");
        stage.setScene(scene);
        stage.show();


    }


    public static void main(String[] args) {
        launch();
    }
}