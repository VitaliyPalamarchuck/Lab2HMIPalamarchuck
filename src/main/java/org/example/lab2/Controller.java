package org.example.lab2;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URISyntaxException;
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
    private TableColumn<Person, String> columnPIP;
    @FXML
    private TableColumn<Person, String> columnPhone;
    @FXML
    private Label tempMessageLabel;

    private Stage newStage;
    private Stage editDialogStage;
    private Parent root;
    private FXMLLoader fxmlLoader =new FXMLLoader();
    private RedactionController editController;
    private Stage primaryStage;


    @FXML
    private TableView<Person> tableAddressBook;

    private CollectionAddressBook addressBookImpl =new CollectionAddressBook();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        try{
            fxmlLoader.setLocation(Controller.class.getResource("RedactionWindow.fxml"));
            root=fxmlLoader.load();
            editController=fxmlLoader.getController();
        }catch (IOException e){
            e.printStackTrace();
        }


        columnPIP.setCellValueFactory(new PropertyValueFactory<Person,String>("PIP"));
        columnPhone.setCellValueFactory(new PropertyValueFactory<Person,String>("Phone"));
        addressBookImpl.getPersonList().addListener(new ListChangeListener<Person>() {
            @Override
            public void onChanged(Change<? extends Person> c) {
                updateCountLabel();
            }
        });
        addressBookImpl.fillTestData();
        tableAddressBook.setItems(addressBookImpl.getPersonList());

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

    private void updateCountLabel(){
        label.setText("Кількість записів: " + addressBookImpl.getPersonList().size());
    }

    public void setNewStage(Stage newStage){
        this.newStage=newStage;
        Controller mainController = fxmlLoader.getController();
        mainController.setNewStage(primaryStage);
    }

    public void showDialog(){
        Person selectedPerson = tableAddressBook.getSelectionModel().getSelectedItem();
        // Якщо запис не вибрано, показати попередження
        if (selectedPerson == null) {
            showWarning(
                    "Попередження",
                    "Рядок не вибрано",
                    "Будь ласка, виберіть запис, який потрібно відредагувати."
            );
            return;
        }

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("RedactionWindow.fxml"));
            Parent root = fxmlLoader.load();
            RedactionController editController = fxmlLoader.getController();
            editController.setPerson(selectedPerson);

            Stage editStage = new Stage();
            editStage.setScene(new Scene(root));
            editStage.setTitle("Редагування даних");

            editStage.getIcons().add(new Image(getClass().getResource("/images/app_icon.png").toExternalForm()));

            editStage.setResizable(false);
            editStage.initModality(Modality.WINDOW_MODAL);
            editStage.initOwner(tableAddressBook.getScene().getWindow());
            editStage.showAndWait();

            tableAddressBook.refresh();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    public void addPerson() {
        try {
            // Завантаження нового вікна
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("RedactionWindow.fxml"));
            Parent root = fxmlLoader.load();

            // Отримання контролера редагування
            RedactionController editController = fxmlLoader.getController();

            // Створення нового об'єкта Person
            Person newPerson = new Person();
            editController.setPerson(newPerson);

            // Створення вікна "Добавити"
            Stage addPersonStage = new Stage();
            addPersonStage.setScene(new Scene(root));
            addPersonStage.setTitle("Додати нового користувача");

            Image icon = new Image(getClass().getResource("/images/app_icon.png").toExternalForm());
            addPersonStage.getIcons().add(icon);
            addPersonStage.setResizable(false);
            addPersonStage.initModality(Modality.WINDOW_MODAL);
            addPersonStage.initOwner(newStage); // Використання головного wікна

            // Чекаємо, поки користувач заповнить дані
            addPersonStage.showAndWait();

            // Перевіряємо, чи є зміна даних, і додаємо у список
            if (newPerson.getPIP() != null && !newPerson.getPIP().isEmpty() &&
                    newPerson.getPhone() != null && !newPerson.getPhone().isEmpty()) {
                addressBookImpl.add(newPerson);
            }

            // Після додавання оновлюємо таблицю
            tableAddressBook.refresh();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    void openWindow(ActionEvent event) throws IOException, URISyntaxException{
        Button clickedButton =(Button) event.getSource();

        switch (clickedButton.getId()){
            case "btnAdd":
                editController.setPerson(new Person());
                showDialog();
                addressBookImpl.add(editController.getPerson());
                break;
            case "btnEdit":
                editController.setPerson((Person) tableAddressBook.getSelectionModel().getSelectedItem());
                showDialog();
                break;
            case "btnDelete":
                addressBookImpl.delete((Person) tableAddressBook.getSelectionModel().getSelectedItem());
                break;
        }
    }



    @FXML
    void information_Alert(ActionEvent event) {
        Person selectedPerson = tableAddressBook.getSelectionModel().getSelectedItem();

        // Якщо запис не вибрано
        if (selectedPerson == null) {
            showWarning(
                    "Попередження",
                    "Рядок не вибрано",
                    "Будь ласка, виберіть запис, який потрібно видалити."
            );
            return;
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Видалення");
        alert.setContentText("Ви впевненні, що хочете видалити запис: " + selectedPerson.getPIP() + "?");

        DialogPane dialogPane = alert.getDialogPane();
        String cssPath = getClass().getResource("/styles/AlertStyles.css").toExternalForm();
        dialogPane.getStylesheets().add(cssPath);

        Stage stage = (Stage) dialogPane.getScene().getWindow();
        stage.getIcons().add(new Image(getClass().getResource("/images/app_icon.png").toExternalForm()));

        var result = alert.showAndWait().orElse(null);

        if (result == ButtonType.OK) {
            addressBookImpl.delete(selectedPerson);
            tableAddressBook.refresh();
            // Встановлення тексту в лейбл із подальшим очищенням
            setTempMessage("Запис успішно видалено!", 3000); // 3 секунди
        } else if (result == ButtonType.CANCEL) {
            setTempMessage("Видалення відмінено.", 3000); // 3 секунди
        }

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

    private void showWarning(String title, String headerText, String contentText) {
        Alert warningAlert = new Alert(Alert.AlertType.WARNING);
        warningAlert.setTitle(title);
        warningAlert.setHeaderText(headerText);
        warningAlert.setContentText(contentText);

        DialogPane dialogPane = warningAlert.getDialogPane();
        String cssPath = getClass().getResource("/styles/AlertStyles.css").toExternalForm();
        dialogPane.getStylesheets().add(cssPath);

        Stage stage = (Stage) warningAlert.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image(getClass().getResource("/images/app_icon.png").toExternalForm()));

        warningAlert.showAndWait();
    }

    private void setTempMessage(String message, long durationMillis) {
        tempMessageLabel.setText(message);

        // Таймер для автоматичного очищення тексту через 'durationMillis'
        Timeline timeline = new Timeline(new KeyFrame(
                javafx.util.Duration.millis(durationMillis),
                event -> tempMessageLabel.setText("")
        ));
        timeline.setCycleCount(1); // Запускається лише раз
        timeline.play();
    }
}